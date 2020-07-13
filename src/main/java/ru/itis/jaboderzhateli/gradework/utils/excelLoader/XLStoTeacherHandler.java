package ru.itis.jaboderzhateli.gradework.utils.excelLoader;

import com.poiji.bind.Poiji;
import org.apache.poi.ss.usermodel.Workbook;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.TeacherPoijiDto;

import java.io.File;
import java.util.List;

public class XLStoTeacherHandler implements FileToPOIHandler<TeacherPoijiDto> {

    @Override
    public List<? extends TeacherPoijiDto> load(File file, TeacherPoijiDto targetObject) {
        return Poiji.fromExcel(file, targetObject.getClass());
    }

    @Override
    public void upload(Workbook workbook) {

    }

}
