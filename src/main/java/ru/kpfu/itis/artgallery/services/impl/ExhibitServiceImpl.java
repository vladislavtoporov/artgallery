package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.repositories.ExhibitRepository;
import ru.kpfu.itis.artgallery.services.ExhibitService;

@Service
public class ExhibitServiceImpl implements ExhibitService {

    private ExhibitRepository exhibitRepository;

    public ExhibitServiceImpl(ExhibitRepository exhibitRepository) {
        this.exhibitRepository = exhibitRepository;
    }

}
