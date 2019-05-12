package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.NewsDTOWithFormat;

import java.util.Set;

public interface NewsService {
    Set<NewsDTOWithFormat> getAllNews();
}
