package ru.tsystems.internetshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.NewsDAO;
import ru.tsystems.internetshop.model.entity.News;

/**
 * This class extends AbstractDAO and implements NewsDAO
 */
@Repository
public class NewsDAOImpl extends AbstractDAO<News, Long> implements NewsDAO {
}
