package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.jaboderzhateli.gradework.models.Channel;
import ru.itis.jaboderzhateli.gradework.models.User;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.repositories.TeacherRepository;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ChatService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final EmployerRepository employerRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/chat")
    public String getChat(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(value = "ch", required = false) Long channelId, ModelMap map) {
        if(channelId != null) {
            Optional<Channel> channelOptional = chatService.getChannel(channelId);
            if(channelOptional.isPresent()) {
                if(!chatService.checkIfUserBelongsToChat(userDetails.getId(), channelId)) {
                    map.put("code", 403);
                    return "error_page";
                }
                List<User> users = chatService.getUsersForChannel(channelId);
                for(User user : users) {
                    if(!user.getId().equals(userDetails.getId())) {
                        switch (user.getRole()) {
                            case STUDENT:
                                map.put("user", studentRepository.findById(user.getId()).get());
                                break;
                            case TEACHER:
                                map.put("user", teacherRepository.findById(user.getId()).get());
                                break;
                            case EMPLOYER:
                                map.put("user", employerRepository.findById(user.getId()).get());
                                break;
                            case ADMINISTRATION:
                                map.put("user", user);
                                break;
                        }
                    }
                }
                map.put("me", userDetails.getUser());
                map.put("messages", chatService.getMessagesForChannel(channelId));
                map.put("pageId", UUID.randomUUID().toString());
                return "main/chat";
            }
        }
        map.put("code", 404);
        return "error_page";
    }

//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/chat")
//    public String getChat(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(value = "ch", required = false) Long channelId, ModelMap map) {
//        if(channelId != null) {
//            Dto channelDto = chatService.getChannel(channelId);
//            if(!channelDto.getStatus().equals(Status.CHANNEL_LOAD_SUCCESS)) {
//                map.put("status", 400);
//                map.put("text", "Такой беседы не существует.");
//                return "error_page";
//            }
//            channelDto = chatService.checkIfUserBelongsToChat(userDetails.getId(), channelId);
//            if(!channelDto.getStatus().equals(Status.USER_BELONGING_TO_CHANNEL_SUCCESS)) {
//                map.put("status", 403);
//                map.put("text", "Вы не состоите в этой беседе.");
//                return "error_page";
//            }
//            List<Message> messages = (List<Message>) chatService.loadMessagesForChannel(channelId).get("messages");
//            map.put("messages", messages);
//
//            List<User> users = (List<User>) chatService.getUsersForChannel(channelId).get("users");
//            for(User user : users) {
//                if(!user.getId().equals(userDetails.getId())) {
//                    map.put("user", user);
//                    break;
//                }
//            }
//            map.put("pageId", UUID.randomUUID().toString());
//            return "main/chat";
//        }
//        Dto dto = chatService.loadLastChannelMessagesForUserId(userDetails.getId());
//        if(!dto.getStatus().equals(Status.MESSAGE_LOAD_LAST_FOR_USER_ID_CHANNELS_SUCCESS)) {
//            map.put("error", true);
//            return "main/chat_browser";
//        }
//        List<Message> messages = (List<Message>) dto.get("messages");
//        map.put("messages", messages);
//        Map<Long, List<User>> channelUsers = new HashMap<>();
//        for(Message message : messages) {
//            Long chId = message.getChannel().getId();
//            List<User> users = (List<User>) chatService.getUsersForChannel(chId).get("users");
//            channelUsers.put(chId, users);
//        }
//        map.put("channelUsers", channelUsers);
//        map.put("me", userDetails.getUser());
//        return "main/chat_browser";
//    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{user-id}/createChat")
    public String createChat(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("user-id") Long userId, ModelMap map) {
        List<Channel> suitables = chatService.checkIfChannelExistsForUsers(userDetails.getId(), userId);
        if(suitables.isEmpty()) {
            return "redirect:/chat?ch=" + chatService.createChannelForUsers(null, userDetails.getId(), userId).getId();
        } else {
            return "redirect:/chat?ch=" + suitables.get(0).getId();
        }
    }
}