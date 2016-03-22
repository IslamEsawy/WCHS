package com.wchs.service;

import com.wchs.model.Product;
import com.wchs.repository.ProductRepository;
import com.wchs.util.BackEndResponse;
import com.wchs.util.MessageCode;
import com.wchs.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Islam on 3/19/2016.
 */
@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public BackEndResponse list() {
        BackEndResponse backEndResponse = new BackEndResponse();
        backEndResponse.setObject(productRepository.list());
        backEndResponse.setResultStatus(ResultStatus.SUCCESS);
        backEndResponse.setMessageCode(MessageCode.SUCCESS);
        return backEndResponse;
    }


    public BackEndResponse save(Product product) {
        System.out.println(product.getName());
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = productRepository.save(product);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(product);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse delete(String[] id) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = ResultStatus.SUCCESS;
        for (String id1 : id){
            System.out.println(id1);
            resultStatus = productRepository.delete(id1);
        }
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse update(Product product) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = productRepository.update(product);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(product);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public Double getRestOfGoodsCapital() {
        return productRepository.getRestOfGoodsCapital();
    }

    public Double getRestOfGoodsProfit() {
        return productRepository.getRestOfGoodsProfit();
    }
}
