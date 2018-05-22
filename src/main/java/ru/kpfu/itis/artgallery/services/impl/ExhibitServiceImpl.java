package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.repositories.ExhibitRepository;
import ru.kpfu.itis.artgallery.repositories.FileRepository;
import ru.kpfu.itis.artgallery.services.ExhibitService;

@Service
public class ExhibitServiceImpl implements ExhibitService {

    private ExhibitRepository exhibitRepository;
    private FileRepository fileRepository;

    @Autowired
    public ExhibitServiceImpl(ExhibitRepository exhibitRepository, FileRepository fileRepository) {
        this.exhibitRepository = exhibitRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public Exhibit getOne(Long id) {
        return exhibitRepository.getOne(id);
    }

    @Override
    public Page<Exhibit> findSimilar(Exhibit exhibit) {
        return exhibitRepository.findAllByAuthor(new PageRequest(0, 10, Sort.Direction.ASC, "name"), exhibit.getAuthor());
    }

    @Override
    public Page<Exhibit> findAllSortByName(int page) {
        return exhibitRepository.findAll(new PageRequest(page, 10, Sort.Direction.ASC, "name"));
    }

    @Override
    public Page<Exhibit> findAllByQuerry(String querry, String filter, int page) {
        if ("author".equals(filter)) {
            return exhibitRepository.findAllByNameContainingOrderByAuthor(new PageRequest(page, 10), querry);
        }
        if ("date".equals(filter)) {
            return exhibitRepository.findAllByNameContainingOrderByTs(new PageRequest(page, 10), querry);
        }
        return new PageImpl<Exhibit>(exhibitRepository.findAll()) {
        };
    }
}
