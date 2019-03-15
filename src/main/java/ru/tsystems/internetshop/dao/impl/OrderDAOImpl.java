package ru.tsystems.internetshop.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.model.entity.Order;


@Repository
public class OrderDAOImpl extends AbstractDAO<Order, Long> implements OrderDAO {
}
