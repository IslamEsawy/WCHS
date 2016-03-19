package com.wchs.repository;

import com.wchs.model.Borrow;
import com.wchs.util.ResultStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Islam on 3/19/2016.
 */
@Repository
public class BorrowRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Borrow> list() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Borrow.class);
        return criteria.list();
    }

    public ResultStatus save(Borrow borrow) {
        Session session = sessionFactory.getCurrentSession();
        session.save(borrow);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus delete(Borrow borrow) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(borrow);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus update(Borrow borrow) {
        Session session = sessionFactory.getCurrentSession();
        session.update(borrow);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }
}
