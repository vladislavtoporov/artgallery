package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.repositories.PrivateMessageRepository;
import ru.kpfu.itis.artgallery.services.PrivateMessageService;

@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

    private PrivateMessageRepository privateMessageRepository;

    public PrivateMessageServiceImpl(PrivateMessageRepository privateMessageRepository) {
        this.privateMessageRepository = privateMessageRepository;
    }


}
