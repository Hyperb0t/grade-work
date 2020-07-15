package ru.itis.jaboderzhateli.gradework.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpEmployerForm;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpStudentForm;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpTeacherForm;
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.Teacher;

import java.util.List;

public interface SignUpService {

    Employer signUp(SignUpEmployerForm form);

    Student signUp(SignUpStudentForm form);

    Teacher signUp(SignUpTeacherForm form);

    Iterable<Student> signUpStudents(MultipartFile file);

    Iterable<Teacher> signUpTeachers(MultipartFile file);


}
