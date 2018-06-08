package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.repositories.ExpositionRepository;
import ru.kpfu.itis.artgallery.services.ExpositionService;

@Service
public class ExpositionServiceImpl implements ExpositionService {

    private ExpositionRepository expositionRepository;

    public ExpositionServiceImpl(ExpositionRepository expositionRepository) {
        this.expositionRepository = expositionRepository;
    }

    @Override
    public Page<Exposition> findAllByQuerry(String querry, String filter, int page) {
        if ("price".equals(filter)) {
            return expositionRepository.findAllByNameContainingOrderByPrice(new PageRequest(page, 10), querry);
        }
        if ("author".equals(filter)) {
            return expositionRepository.findAllByNameContainingOrderByOwner(new PageRequest(page, 10), querry);
        }
        if ("date".equals(filter)) {
            return expositionRepository.findAllByNameContainingOrderByStart(new PageRequest(page, 10), querry);
        }
        return new PageImpl<Exposition>(expositionRepository.findAll()) {
        };
    }

    @Override
    public Page<Exposition> findAll(int page) {
        return expositionRepository.findAll(new PageRequest(page, 10, Sort.Direction.ASC, "name"));
    }

    @Override
    public Page<Exposition> findSimilar(Exposition exposition) {
        return expositionRepository.findAllByOwner(new PageRequest(0, 10, Sort.Direction.ASC, "name"), exposition.getOwner());
    }

    @Override
    public Exposition getOne(Long id) {
        return expositionRepository.getOne(id);
    }
}
