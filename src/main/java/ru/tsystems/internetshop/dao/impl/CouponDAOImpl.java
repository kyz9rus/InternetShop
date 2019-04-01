package ru.tsystems.internetshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.CouponDAO;
import ru.tsystems.internetshop.model.entity.Coupon;

import javax.persistence.TypedQuery;

@Repository
public class CouponDAOImpl extends AbstractDAO<Coupon, Integer> implements CouponDAO {

    @Override
    public Coupon findCouponByValue(String value) {
        String queryString = "SELECT c FROM coupon c where c.value = :value";

        TypedQuery<Coupon> query = sessionFactory.getCurrentSession().createQuery(queryString, Coupon.class);
        query.setParameter("value", value);

        if (!query.getResultList().isEmpty())
            return query.getResultList().get(0);
        else
            return null;
    }
}
