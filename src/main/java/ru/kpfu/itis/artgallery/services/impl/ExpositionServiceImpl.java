package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    public Page<Exposition> findAllBySort(String querry, String sort, int page) {
        if ("price".equals(sort)) {
            return expositionRepository.findAllByNameContainingOrderByPrice(new PageRequest(page, 10), querry);
        }
        if ("date".equals(sort)) {
            return expositionRepository.findAllByNameContainingOrderByStart(new PageRequest(page, 10), querry);
        }
        return new PageImpl<Exposition>(expositionRepository.findAll()) {
        };
    }
}
