package ru.itis.jaboderzhateli.gradework.utils.excelLoader;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.List;

public interface FileToPOIHandler<T> {

    List<? extends T> load(File file, T targetObject);

    void upload(Workbook workbook);

}
