package ru.itis.jaboderzhateli.gradework.utils.excelLoader;


import com.poiji.bind.Poiji;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.StudentPoijiDto;

import java.io.File;
import java.util.List;

@Component
public class XLStoStudentHandler implements FileToPOIHandler<StudentPoijiDto> {

    @Override
    public List<? extends StudentPoijiDto> load(File file, StudentPoijiDto targetObject) {
        return Poiji.fromExcel(file, targetObject.getClass());
    }

    @Override
    public void upload(Workbook workbook) {

    }


}
