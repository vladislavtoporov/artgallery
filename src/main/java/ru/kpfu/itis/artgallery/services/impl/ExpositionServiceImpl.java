package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.repositories.ExpositionRepository;
import ru.kpfu.itis.artgallery.services.ExpositionService;

@Service
public class ExpositionServiceImpl implements ExpositionService {

    private ExpositionRepository expositionRepository;

    public ExpositionServiceImpl(ExpositionRepository expositionRepository) {
        this.expositionRepository = expositionRepository;
    }


}
