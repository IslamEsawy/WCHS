package com.wchs.repository;

import com.wchs.model.Category;
import com.wchs.model.Miscellaneous;
import com.wchs.util.ResultStatus;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Islam on 3/19/2016.
 */
@Repository
public class CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Category> list() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
        return criteria.list();
    }

    public ResultStatus save(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.save(category);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus delete(Category category) {
        Session session = sessionFactory.getCurrentSession();
        Object persistentInstance = session.load(Category.class, category.getId());
        if (persistentInstance != null) {
            session.delete(persistentInstance);
        }
        try {
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }


    public ResultStatus update(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.update(category);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus update() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "update com.wchs.model.Category cat1 set cat1.capital = (select SUM(p.totalSellingPrice) from com.wchs.model.Category c, com.wchs.model.Product p where p.category.id = c.id and c.id = cat1.id) , cat1.profit = (select SUM(p.netProfit) from com.wchs.model.Category c, com.wchs.model.Product p where p.category.id = c.id and c.id = cat1.id)";
        Query query = null;
        try {
            query = session.createQuery(hql);
            int rowCount = query.executeUpdate();
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public Double getSumOfCapital() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
        criteria.setProjection(Projections.sum("capital"));
        return (Double)criteria.uniqueResult();
    }

    public Double getSumOfProfit() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
        criteria.setProjection(Projections.sum("profit"));
        return (Double)criteria.uniqueResult();
    }
}
