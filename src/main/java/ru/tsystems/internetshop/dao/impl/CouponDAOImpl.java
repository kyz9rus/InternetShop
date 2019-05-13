package ru.tsystems.internetshop.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.CouponDAO;
import ru.tsystems.internetshop.exception.DAOException;
import ru.tsystems.internetshop.model.entity.Coupon;

import javax.persistence.TypedQuery;

/**
 * This class implements CouponDAO and override its methods
 */
@Repository
public class CouponDAOImpl extends AbstractDAO<Coupon, Integer> implements CouponDAO {

    private final Logger fileLogger = Logger.getLogger("fileLogger");

    /**
     * This method get top 10 clients from database using HQL
     *
     * @param value string value, which unique identifies coupon
     * @return founded coupon
     */
    @Override
    public Coupon findCouponByValue(String value) {
        try {
            String queryString = "SELECT c FROM coupon c where c.value = :value";

            TypedQuery<Coupon> query = sessionFactory.getCurrentSession().createQuery(queryString, Coupon.class);
            query.setParameter("value", value);

            if (!query.getResultList().isEmpty())
                return query.getResultList().get(0);
            else
                return null;
        } catch (Exception e) {
            fileLogger.error(e.getMessage());
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
