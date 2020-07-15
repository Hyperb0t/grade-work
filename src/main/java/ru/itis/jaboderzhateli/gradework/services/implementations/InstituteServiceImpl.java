package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.Institute;
import ru.itis.jaboderzhateli.gradework.repositories.InstituteRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.InstituteService;

import java.util.List;

@Service
@AllArgsConstructor
public class InstituteServiceImpl implements InstituteService {

    private final InstituteRepository instituteRepository;

    @Override
    public List<Institute> getAllInstitutes() {
        return instituteRepository.findAll();
    }
}
