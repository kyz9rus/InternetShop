//package ru.tsystems.internetshop.dao;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.tsystems.internetshop.model.ClientDto;
//import ru.tsystems.internetshop.model.OrderDto;
//
//import javax.persistence.TypedQuery;
//import java.util.List;
//
//
//@Transactional
//@Repository
//public class OrderDAOImpl implements OrderDAO {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Override
//    public OrderDto findById(int id) {
//        return sessionFactory.getCurrentSession().find(OrderDto.class, id);
//    }
//
//    @Override
//    public void saveOrder(OrderDto order) {
//        sessionFactory.getCurrentSession().save(order);
//    }
//}
