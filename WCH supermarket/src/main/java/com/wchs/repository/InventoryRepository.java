package com.wchs.repository;

import com.wchs.model.Category;
import com.wchs.model.Inventory;
import com.wchs.model.Miscellaneous;
import com.wchs.model.Product;
import com.wchs.util.ResultStatus;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
public class InventoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Inventory> list() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Inventory.class);
        return criteria.list();
    }

    public ResultStatus reset() {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus save(Inventory inventory) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(inventory);
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }


/*    public Inventory doInventory() {

        return null;
    }*/


}
