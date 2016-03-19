package com.wchs.repository;

import com.wchs.model.Customer;
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
public class CustomerRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Customer> list() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
        return criteria.list();
    }

    public ResultStatus save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(customer);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus delete(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(customer);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus update(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.update(customer);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }
}
