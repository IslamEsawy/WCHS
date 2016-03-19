package com.wchs.repository;

import com.wchs.model.Product;
import com.wchs.util.ResultStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Islam on 3/19/2016.
 */

@Repository
public class ProductRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Product> list() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
        return criteria.list();
    }

    public ResultStatus save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus delete(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(product);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }
}
