package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.repositories.PostRepository;
import ru.kpfu.itis.artgallery.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
