package ru.itis.jaboderzhateli.gradework.utils.excelLoader;

import com.poiji.exception.PoijiExcelType;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.jdbc.Work;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.Teacher;

import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ExcelConverter {

    <T> List<T> upload(File file, Class<T> clazz);

    <T> List<T> upload(InputStream stream, Class<T> clazz, Enum excelType);

    void download(Workbook workbook);

    void downloadStudents(List<Map<Student, String>> list, HttpServletResponse response);

    void downloadTeachers(List<Map<Teacher, String>> list, HttpServletResponse response);

}
