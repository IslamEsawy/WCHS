package com.wchs.repository;

import com.wchs.model.Borrow;
import com.wchs.model.Category;
import com.wchs.model.Miscellaneous;
import com.wchs.model.Product;
import com.wchs.util.ResultStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
        Object persistentInstance = session.load(Borrow.class, borrow.getBid());
        if (persistentInstance != null) {
            session.delete(persistentInstance);
        }
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

    public ResultStatus makePaid(String id) {
        Integer idI = Integer.parseInt(id);
        System.out.println(idI);
        Session session = sessionFactory.getCurrentSession();
        Borrow myObject = (Borrow)session.load(Borrow.class,idI);
        myObject.setIsPaid("YES");
        session.update(myObject);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public Double getSum() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Borrow.class);
        criteria.add(Restrictions.eq("isPaid", "NO"));
        criteria.setProjection(Projections.sum("amount"));
        return (Double)criteria.uniqueResult();
    }
}
