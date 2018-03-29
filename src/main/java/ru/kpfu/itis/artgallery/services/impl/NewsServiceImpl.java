package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.repositories.NewsRepository;
import ru.kpfu.itis.artgallery.services.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


}
