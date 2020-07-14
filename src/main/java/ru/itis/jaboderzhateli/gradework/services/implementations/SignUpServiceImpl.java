package ru.itis.jaboderzhateli.gradework.services.implementations;

import com.poiji.exception.PoijiExcelType;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpEmployerForm;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.StudentPoijiDto;
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.Role;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.SignUpService;
import ru.itis.jaboderzhateli.gradework.utils.excelLoader.FileToPOJOHandler;

import java.io.IOException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final EmployerRepository employerRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileToPOJOHandler poiHandler;

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

    @Override
    public void signUpStudent(MultipartFile file) {

        var fileName = file.getOriginalFilename();
        var filenamePostfix = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf('.'));

        if (filenamePostfix.equals("xls")) {
            try {
                poiHandler.upload(file.getInputStream(), StudentPoijiDto.class, PoijiExcelType.XLS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (filenamePostfix.equals("xlsx")) {
            try {
                poiHandler.upload(file.getInputStream(), StudentPoijiDto.class, PoijiExcelType.XLSX);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
