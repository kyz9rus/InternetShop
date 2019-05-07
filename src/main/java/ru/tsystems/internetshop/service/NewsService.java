package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.NewsDTO;

import java.util.Set;

public interface NewsService {
    Set<NewsDTO> getAllNews();
}
