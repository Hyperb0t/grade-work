package ru.itis.jaboderzhateli.gradework.utils.excelLoader;

import org.springframework.web.multipart.MultipartFile;

public interface ExtensionParser {
    Enum<?> parseFileExtension(MultipartFile file);
}
