package com.wchs.repository;

import com.wchs.model.Category;
import com.wchs.model.Product;
import com.wchs.util.ResultStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
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

    public List<Product> list(Category category) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
        criteria.add(Restrictions.eq("category.id", category.getId()));
        return criteria.list();
    }

    public ResultStatus save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println(product.getName());
        session.save(product);

        try {
            session.flush();
        } catch (Exception e) {
            return ResultStatus.FAILED;
        }
        return ResultStatus.SUCCESS;
    }

    public ResultStatus delete(String barcode) {
        System.out.println("Barcode: " + barcode);
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Product.class);
        Product product = (Product)criteria.add(Restrictions.eq("barcode", barcode))
                .uniqueResult();
        product.setCategory(new Category());
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

    public Double getRestOfGoodsCapital() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
        criteria.setProjection(Projections.sqlProjection("sum(buyingPricePerItem * totalAvailableItems) as sum", new String[]{"sum"}, new Type[]{StandardBasicTypes.DOUBLE}));
        return (Double) criteria.uniqueResult();
    }

    public Double getRestOfGoodsProfit() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
        criteria.setProjection(Projections.sqlProjection("sum((sellingPricePerItem-buyingPricePerItem) * totalAvailableItems) as sum", new String[]{"sum"}, new Type[]{StandardBasicTypes.DOUBLE}));
        return (Double) criteria.uniqueResult();
    }
}
