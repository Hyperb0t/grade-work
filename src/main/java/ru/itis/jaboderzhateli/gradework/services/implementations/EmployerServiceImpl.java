package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.User;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.UserRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.EmployerService;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;

    @Override
    public Employer edit(Employer employer, Map<String, String> params) {
        return null;
    }

    @Override
    public Employer edit(User user, Map<String, String> params) {
        var employer = employerRepository.getOne(user.getId());

        employer.setName(params.get("name"));
        employer.setSurname(params.get("surname"));
        employer.setMiddleName(params.get("middleName"));
        employer.setPhone(params.get("phone"));
        employer.setEmail(params.get("email"));
        employer.setCompanyName(params.get("companyName"));
        employer.setPsrn(params.get("psrn"));
        employer.setBio(params.get("link"));

        return employerRepository.save(employer);
    }

    @Override
    public Employer get(Long id) {
        return null;
    }
}
