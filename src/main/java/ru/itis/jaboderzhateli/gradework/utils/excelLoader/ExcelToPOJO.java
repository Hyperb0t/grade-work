package ru.itis.jaboderzhateli.gradework.utils.excelLoader;


import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.Teacher;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ConverterService;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ExcelToPOJO implements FileToPOJOHandler {

    private final ConverterService converterService;

    @Override
    public <T> List<T> upload(File file, Class<T> clazz) {
        return Poiji.fromExcel(file, clazz);
    }

    @Override
    public <T> List<T> upload(InputStream stream, Class<T> clazz, Enum excelType) {
        return Poiji.fromExcel(stream, (PoijiExcelType) excelType, clazz);
    }

    @Override
    public void download(Workbook workbook) {

    }

    @SneakyThrows
    public void downloadStudents(List<Map<Student, String>> list) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Students");
        int i = 0;
        for (Map<Student, String> students : list) {
            for (Map.Entry<Student, String> student : students.entrySet()) {
                Row row = sheet.createRow(i);
                i++;
                row.createCell(0).setCellValue(student.getKey().getEmail());
                row.createCell(1).setCellValue(student.getValue());
            }
        }

        book.write(new FileOutputStream("students"));
        book.close();
    }

    @SneakyThrows
    public void downloadTeachers(List<Map<Teacher, String>> list) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Students");
        int i = 0;
        for (Map<Teacher, String> teachers : list) {
            for (Map.Entry<Teacher, String> teacher : teachers.entrySet()) {
                Row row = sheet.createRow(i);
                i++;
                row.createCell(0).setCellValue(teacher.getKey().getEmail());
                row.createCell(1).setCellValue(teacher.getValue());
            }
        }

        book.write(new FileOutputStream("teachers"));
        book.close();

    }
}
