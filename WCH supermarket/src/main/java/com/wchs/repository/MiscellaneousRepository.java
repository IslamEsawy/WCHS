package com.wchs.repository;

import java.util.ArrayList;
import java.util.List;

import com.wchs.model.Miscellaneous;
import com.wchs.util.ResultStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
