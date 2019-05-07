package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.NewsDAO;
import ru.tsystems.internetshop.model.DTO.NewsDTO;
import ru.tsystems.internetshop.model.entity.News;
import ru.tsystems.internetshop.service.NewsService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public Set<NewsDTO> getAllNews() {
        Set<NewsDTO> newsDTO = new HashSet<>();

        List<News> news = newsDAO.findAll();

        for (News news1 : news)
            newsDTO.add(mapper.convertToDto(news1));

        return newsDTO;
    }
}