package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public Page<Exhibit> findAllBySort(String querry, String sort, int page) {
        if ("author".equals(sort)) {
            return exhibitRepository.findAllByNameContainingOrderByAuthor(new PageRequest(page, 10), querry);
        }
        if ("date".equals(sort)) {
            return exhibitRepository.findAllByNameContainingOrderByTs(new PageRequest(page, 10), querry);
        }
        return new PageImpl<Exhibit>(exhibitRepository.findAll()) {
        };
    }
}
