package ru.itis.jaboderzhateli.gradework.services.interfaces;

import java.util.List;
import java.util.Map;

public interface DynamicArgumentsParser {

    List<String> parse(Map<String, String> arguments, String regex);

}
