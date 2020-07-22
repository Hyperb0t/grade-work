package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.Teacher;
import ru.itis.jaboderzhateli.gradework.models.User;
import ru.itis.jaboderzhateli.gradework.repositories.TeacherRepository;
import ru.itis.jaboderzhateli.gradework.repositories.UserRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.InstituteService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.TeacherService;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final InstituteService instituteService;

    @Override
    public Teacher edit(Teacher teacher, Map<String, String> params) {
        return null;
    }

    @Override
    public Teacher edit(User user, Map<String, String> params) {

        var teacher = teacherRepository.getOne(user.getId());

        teacher.setEmail(params.get("email"));
        teacher.setSurname(params.get("surname"));
        teacher.setPosition(params.get("position"));
        teacher.setPhone(params.get("phone"));
        teacher.setName(params.get("name"));
        teacher.setMiddleName(params.get("middleName"));
        teacher.setLink(params.get("link"));
        teacher.setInstitute(instituteService.getInstitute(params.get("institute")));
        teacher.setExperience(Byte.valueOf(params.get("experience")));

        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher get(Long id) {
        return null;
    }
}
