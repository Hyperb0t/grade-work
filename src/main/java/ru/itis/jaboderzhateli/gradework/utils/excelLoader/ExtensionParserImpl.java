package ru.itis.jaboderzhateli.gradework.utils.excelLoader;

import com.poiji.exception.PoijiExcelType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Component
public class ExtensionParserImpl implements ExtensionParser {

    public Enum<?> parseFileExtension(MultipartFile file) {
        var fileName = file.getOriginalFilename();
        var filenamePostfix = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf('.'));

        //TODO Review код

        Enum<?> header;

        switch (filenamePostfix) {
            case ("xlsx"):
                header = PoijiExcelType.XLSX;
                break;
//            case ("xls"):
//                header = PoijiExcelType.XLS;
//                break;
            default:
                header = PoijiExcelType.XLS;
                break;
        }
        return header;
    }
}
