package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.User;

import java.util.Map;
import java.util.Optional;

public interface EmployerService {

    Employer edit(Employer employer, Map<String, String> params);

    Employer edit(User user,Map<String, String> params);

    Employer get(Long id);

}
