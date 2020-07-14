package ru.itis.jaboderzhateli.gradework.utils.excelLoader;

import com.poiji.exception.PoijiExcelType;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.jdbc.Work;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface FileToPOJOHandler {

    <T> List<T> upload(File file, Class<T> clazz);

    <T> List<T> upload(InputStream stream, Class<T> clazz, Enum excelType);

    void download(Workbook workbook);

}
