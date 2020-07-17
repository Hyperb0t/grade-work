package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.User;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface ResumeService {
    void createResume(User user, Map<String, String> resumeParams, Locale locale);
}
