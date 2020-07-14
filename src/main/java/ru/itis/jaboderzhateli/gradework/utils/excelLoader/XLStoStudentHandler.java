package ru.itis.jaboderzhateli.gradework.utils.excelLoader;


import com.poiji.bind.Poiji;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class XLStoStudentHandler implements FileToPOIHandler {

//    @Override
//    public List<? extends StudentPoijiDto> upload(File file, Class<StudentPoijiDto> clazz) {
//        return Poiji.fromExcel(file, clazz);
//    }

    @Override
    public List<?> upload(File file, Class<?> clazz) {
        return Poiji.fromExcel(file, clazz);
    }

    @Override
    public void download(Workbook workbook) {

    }


}
