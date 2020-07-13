package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpEmployerForm;
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.Role;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.SignUpService;

@Service
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final EmployerRepository employerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpEmployerForm form) {

        var employer = Employer.builder()
                .bio(form.getBio())
                .companyName(form.getCompanyName())
                .email(form.getEmail())
                .juristicInfo(form.getJuristicInfo())
                .phone(form.getPhone())
                .build();

        employer.setLogin(form.getLogin());
        employer.setPassword(passwordEncoder.encode(form.getPassword()));
        employer.setRole(Role.EMPLOYER);

        employerRepository.save(employer);
    }
}
