package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.NewsDTOWithFormat;

import java.util.Set;

/**
 * This is interface, which declares methods for managing news
 */
public interface NewsService {
    Set<NewsDTOWithFormat> getAllNews();
}
