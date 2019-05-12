package ru.tsystems.internetshop.util;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tsystems.internetshop.model.DTO.NewsDTO;
import ru.tsystems.internetshop.model.DTO.NewsDTOWithFormat;
import ru.tsystems.internetshop.service.NewsService;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * This class contains set of news, which is showed in the main page
 */
public class NewsInfo implements Serializable {

    @Autowired
    private NewsService newsService;

    private Set<NewsDTOWithFormat> news;

    public NewsInfo() {
        news = new HashSet<>();
    }

    /**
     * This method gets set of news (with dateTime format)
     *
     * @return set of news
     */
    public Set<NewsDTOWithFormat> getNews() {
        return news;
    }

    /**
     * This method adds news in set of news
     *
     * @param newsInfo object from AvonShopNews application
     */
    public void addNews(ru.tsystems.avonshopnews.model.NewsInfo newsInfo) {
        NewsDTOWithFormat newsDTOWithFormat = new NewsDTOWithFormat();

        newsDTOWithFormat.setId(newsInfo.getId());
        newsDTOWithFormat.setArticle(newsInfo.getArticle());
        newsDTOWithFormat.setWritingDate(newsInfo.getWritingDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        newsDTOWithFormat.setText(newsInfo.getText());

        System.out.println("Adding news: newsINFO: " + newsInfo + " | newsDTOWithFormat: " + newsDTOWithFormat);

        this.news.add(newsDTOWithFormat);
    }

    /**
     * This method initializes set of news from database
     */
    @PostConstruct
    public void init() {
        news = new HashSet<>(newsService.getAllNews());
    }
}
