package ru.tsystems.internetshop.util;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tsystems.internetshop.model.DTO.NewsDTO;
import ru.tsystems.internetshop.service.NewsService;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This class contains set of news, which is showed in the main page
 */
public class NewsInfo implements Serializable {

    @Autowired
    private NewsService newsService;

    private Set<NewsDTO> news;

    public NewsInfo() {
        news = new HashSet<>();
    }

    public Set<NewsDTO> getNews() {
        return news;
    }

    public void addNews(ru.tsystems.avonshopnews.model.NewsInfo newsInfo) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(newsInfo.getId());
        newsDTO.setArticle(newsInfo.getArticle());
        newsDTO.setWritingDate(newsInfo.getWritingDate());
        newsDTO.setText(newsInfo.getText());

        this.news.add(newsDTO);
    }

    @PostConstruct
    public void init() {
        news = new HashSet<>(newsService.getAllNews());
    }
}
