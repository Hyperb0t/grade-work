package ru.itis.jaboderzhateli.gradework.services.implementations;

import ru.itis.jaboderzhateli.gradework.services.interfaces.PasswordGenerationService;

import java.util.UUID;

public class PasswordGenerationServiceImpl implements PasswordGenerationService {
    @Override
    public String generate() {
        return UUID.randomUUID().toString().replace("-","").substring(22);
    }
}
