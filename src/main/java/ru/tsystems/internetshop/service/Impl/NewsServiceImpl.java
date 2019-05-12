package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.NewsDAO;
import ru.tsystems.internetshop.model.DTO.NewsDTO;
import ru.tsystems.internetshop.model.DTO.NewsDTOWithFormat;
import ru.tsystems.internetshop.model.entity.News;
import ru.tsystems.internetshop.service.NewsService;
import ru.tsystems.internetshop.util.Mapper;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
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
    public Set<NewsDTOWithFormat> getAllNews() {
        Set<NewsDTOWithFormat> newsDTOWithFormatList = new HashSet<>();

        List<News> news = newsDAO.findAll();

        for (News news1 : news) {
            NewsDTO newsDTO1 = mapper.convertToDto(news1);

            NewsDTOWithFormat newsDTOWithFormat = new NewsDTOWithFormat();
            newsDTOWithFormat.setId(newsDTO1.getId());
            newsDTOWithFormat.setArticle(newsDTO1.getArticle());
            newsDTOWithFormat.setText(newsDTO1.getText());
            newsDTOWithFormat.setWritingDate(newsDTO1.getWritingDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));

            newsDTOWithFormatList.add(newsDTOWithFormat);
        }

        return newsDTOWithFormatList;
    }
}