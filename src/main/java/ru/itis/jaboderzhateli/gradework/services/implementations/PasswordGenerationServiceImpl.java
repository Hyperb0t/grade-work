package ru.itis.jaboderzhateli.gradework.services.implementations;

import org.springframework.stereotype.Component;
import ru.itis.jaboderzhateli.gradework.services.interfaces.PasswordGenerationService;

import java.util.UUID;

@Component
public class PasswordGenerationServiceImpl implements PasswordGenerationService {
    @Override
    public String generate() {
        return UUID.randomUUID().toString().replace("-","").substring(22);
    }
}
