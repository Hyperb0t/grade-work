package ru.itis.jaboderzhateli.gradework.utils.excelLoader;


import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.util.List;

@Component
public class ExcelToPOJO implements FileToPOJOHandler {

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
}
