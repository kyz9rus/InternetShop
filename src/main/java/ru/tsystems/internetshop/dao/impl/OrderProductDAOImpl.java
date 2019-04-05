package ru.tsystems.internetshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.OrderProductDAO;
import ru.tsystems.internetshop.model.entity.OrderProduct;
//import ru.tsystems.internetshop.model.entity.OrderProductId;

@Repository
public class OrderProductDAOImpl extends AbstractDAO<OrderProduct, Long> implements OrderProductDAO {

}
