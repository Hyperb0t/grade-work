package ru.itis.jaboderzhateli.gradework.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpEmployerForm;

public interface SignUpService {

    void signUp(SignUpEmployerForm signUpEmployerForm);

    void signUpStudent(MultipartFile file);

    void signUpTeacher(MultipartFile file);

}
