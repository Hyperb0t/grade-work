package ru.itis.jaboderzhateli.gradework.utils.excelLoader;

import com.poiji.exception.PoijiExcelType;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.jdbc.Work;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface FileToPOJOHandler {

    List<?> upload(File file, Class<?> clazz);
    List<?> upload (InputStream stream, Class<?> clazz, PoijiExcelType excelType);

    void download(Workbook workbook);

}
