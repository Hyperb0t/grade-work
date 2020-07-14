package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.dto.poijiDto.StudentPoijiDto;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.TeacherPoijiDto;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.Teacher;

import java.util.List;

public interface ConverterService {

    Student convert(StudentPoijiDto studentDto);

    Teacher convert(TeacherPoijiDto teacherDto);

    List<Student> convertStudents(List<StudentPoijiDto> studentDtos);

    List<Teacher> convertTeachers(List<TeacherPoijiDto> teacherDtos);

}
