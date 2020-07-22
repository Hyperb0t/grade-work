package ru.itis.jaboderzhateli.gradework.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import ru.itis.jaboderzhateli.gradework.models.Channel;
import ru.itis.jaboderzhateli.gradework.models.Message;
import ru.itis.jaboderzhateli.gradework.models.User;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.repositories.TeacherRepository;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ChatService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@AllArgsConstructor
public class MessagesController {
    private static final Map<Pair<String, Channel>, List<Long>> map = new HashMap<>();

    private ChatService chatService;
    private ObjectMapper objectMapper;
    private FreeMarkerConfig freeMarkerConfig;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private EmployerRepository employerRepository;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                 @RequestParam("ch") Long channelId, @RequestBody String jsonMessage) {
        try {
            Map response = objectMapper.readValue(jsonMessage, Map.class);

            Optional<Channel> channelOptional = chatService.getChannel(channelId);
            if(channelOptional.isEmpty() || response.get("content") == null
                    || ((String) response.get("content")).trim().equals("") || response.get("page_id") == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Channel channel = channelOptional.get();
            String pageId = (String) response.get("page_id");

            Message message = chatService.saveMessage(channelId, userDetails.getId(), (String) response.get("content"));
            if(message.getId() == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
//            Message message = (Message) chatService.getMessage((Long) messageDto.get("message_id")).get("message");

            // LONG POLLING
            for(Pair<String, Channel> pair : map.keySet()) {
                if(pair.getSecond().equals(channel)) {
                    synchronized (map.get(pair)) {
                        map.get(pair).add(message.getId());
                        map.get(pair).notify();
                    }
                }
            }

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (IOException e) {
            throw new IllegalArgumentException("Unacceptable received message.");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/messages")
    public ResponseEntity<String> getMessages(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestParam("ch") Long channelId,
                                              @RequestParam("page_id") String pageId,
                                              HttpServletRequest request) {
        Optional<Channel> channelOptional = chatService.getChannel(channelId);
        if(channelOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Channel channel = channelOptional.get();

        if(!map.containsKey(Pair.of(pageId, channel))) {
            map.put(Pair.of(pageId, channel), new ArrayList<>());
        }

        synchronized (map.get(Pair.of(pageId, channel))) {
            if(map.get(Pair.of(pageId, channel)).isEmpty()) {
                try {
                    map.get(Pair.of(pageId, channel)).wait();
                } catch (InterruptedException e) {
                    throw new IllegalStateException("Channel messages wait exception.", e);
                }
            }
        }

        List<Long> messagesId = map.get(Pair.of(pageId, channel));
        String messagesHtml = createMessagesHtml(messagesId, userDetails.getUser(), request);

        map.get(Pair.of(pageId, channel)).clear();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "text/html;charset=UTF-8");
        return new ResponseEntity<>(messagesHtml, headers, HttpStatus.OK);
    }

    private String createMessagesHtml(List<Long> messagesId, User me, HttpServletRequest request) {
        try {
            Configuration configuration = freeMarkerConfig.getConfiguration();
            configuration.setEncoding(Locale.getDefault(), "UTF-8");
            configuration.setSharedVariable("rc", new RequestContext(request));
            Template template = configuration.getTemplate("message.ftl");
            StringBuilder messagesHtml = new StringBuilder();
            for(Long messageId : messagesId) {
                Message message = chatService.getMessage(messageId).get();
                Map<String, Object> attributes = new HashMap();
                attributes.put("message", message);
                attributes.put("me", me);
                switch (message.getAuthor().getRole()) {
                    case STUDENT:
                        attributes.put("user", studentRepository.findById(message.getAuthor().getId()).get());
                        break;
                    case TEACHER:
                        attributes.put("user", teacherRepository.findById(message.getAuthor().getId()).get());
                        break;
                    case EMPLOYER:
                        attributes.put("user", employerRepository.findById(message.getAuthor().getId()).get());
                        break;
                    case ADMINISTRATION:
                        attributes.put("user", me);
                        break;
                }
                messagesHtml.append(FreeMarkerTemplateUtils.processTemplateIntoString(template, attributes));
            }
            return messagesHtml.toString();
        } catch (IOException e) {
            throw new IllegalStateException("template creation exception.", e);
        } catch (TemplateException e) {
            throw new IllegalStateException("template processing exception.", e);
        }
    }
}