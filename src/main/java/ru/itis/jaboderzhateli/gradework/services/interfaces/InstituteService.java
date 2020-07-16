package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.Institute;

import java.util.List;
import java.util.Optional;

public interface InstituteService {

    List<Institute> getAllInstitutes();

    Institute getInstitute(String instituteName);
}
