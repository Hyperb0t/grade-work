package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.Institute;
import ru.itis.jaboderzhateli.gradework.repositories.InstituteRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.InstituteService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InstituteServiceImpl implements InstituteService {

    private final InstituteRepository instituteRepository;

    @Override
    public List<Institute> getAllInstitutes() {
        return instituteRepository.findAll();
    }

    @Override
    public Institute getInstitute(String instituteName) {
        Institute institute;
        var instituteCandidate = instituteRepository.findByName(instituteName);
        institute = instituteCandidate.orElseThrow(() -> new IllegalArgumentException(
                "Institute with specified name " + instituteName + " does not exist"));
        return institute;
    }


}
