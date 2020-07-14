package ru.itis.jaboderzhateli.gradework.services.implementations;

import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.StudentPoijiDto;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.TeacherPoijiDto;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.Teacher;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ConverterService;

@Service
public class ConverterServiceImpl implements ConverterService {
    @Override
    public Student convert(StudentPoijiDto studentDto) {
        return null;
    }

    @Override
    public Teacher convert(TeacherPoijiDto teacherDto) {
        return null;
    }
}
