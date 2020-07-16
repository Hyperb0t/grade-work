package ru.itis.jaboderzhateli.gradework.services.implementations;

import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.services.interfaces.DynamicArgumentsParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DynamicArgumentsParserImpl implements DynamicArgumentsParser {

    @Override
    public List<String> parse(Map<String, String> arguments, String regex) {

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        List<String> resultList = new ArrayList<>();

        for (Map.Entry<String, String> map : arguments.entrySet()) {
            if (pattern.matcher(map.getKey()).find()) {
                resultList.add(map.getValue());
            }
        }
        return resultList;
    }
}
