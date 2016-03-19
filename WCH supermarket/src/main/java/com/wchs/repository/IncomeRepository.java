package com.wchs.repository;

import com.wchs.model.Income;
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
public class IncomeRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Income> list() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Income.class);
        return criteria.list();
    }

    public ResultStatus save(Income income) {
        Session session = sessionFactory.getCurrentSession();
        session.save(income);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus delete(Income income) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(income);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus update(Income income) {
        Session session = sessionFactory.getCurrentSession();
        session.update(income);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }
}
