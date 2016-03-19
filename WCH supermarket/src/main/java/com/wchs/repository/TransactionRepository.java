package com.wchs.repository;

import com.wchs.model.Customer;
import com.wchs.model.Transaction;
import com.wchs.util.ResultStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Islam on 3/19/2016.
 */

@Repository
public class TransactionRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Transaction> list() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Transaction.class);
        return criteria.list();
    }

    public List<Transaction> list(Customer customer) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Transaction.class);
        criteria.add(Restrictions.eq("cid", customer.getCid()));
        return criteria.list();
    }

    public ResultStatus save(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.save(transaction);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus delete(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(transaction);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus update(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.update(transaction);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }
}
