package com.wchs.repository;

import java.util.ArrayList;
import java.util.List;

import com.wchs.model.Miscellaneous;
import com.wchs.model.Product;
import com.wchs.util.ResultStatus;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MiscellaneousRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Miscellaneous> list() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Miscellaneous.class);
        return criteria.list();
    }

    public ResultStatus save(Miscellaneous miscellaneous) {
        Session session = sessionFactory.getCurrentSession();
        session.save(miscellaneous);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus delete(String id) {
        Integer idI = Integer.parseInt(id);
        System.out.println(idI);
        Session session = sessionFactory.getCurrentSession();
        Miscellaneous myObject = (Miscellaneous)session.load(Miscellaneous.class,idI);
        session.delete(myObject);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }


    public ResultStatus update(Miscellaneous miscellaneous) {
        Session session = sessionFactory.getCurrentSession();
        session.update(miscellaneous);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public Double getSum() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Miscellaneous.class);
        criteria.setProjection(Projections.sum("price"));
        return (Double)criteria.uniqueResult();
    }
}
