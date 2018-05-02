package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.repositories.ExhibitRepository;
import ru.kpfu.itis.artgallery.services.ExhibitService;

import java.util.List;

@Service
public class ExhibitServiceImpl implements ExhibitService {

    private ExhibitRepository exhibitRepository;

    public ExhibitServiceImpl(ExhibitRepository exhibitRepository) {
        this.exhibitRepository = exhibitRepository;
    }

    @Override
    public Exhibit getOne(Long id) {
        return exhibitRepository.getOne(id);
    }

    @Override
    public List<Exhibit> findSimilar(Exposition exposition) {
        return exhibitRepository.findAll();
    }
}
