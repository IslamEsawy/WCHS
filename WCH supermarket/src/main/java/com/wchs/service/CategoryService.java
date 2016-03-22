package com.wchs.service;

import com.wchs.model.Category;
import com.wchs.model.Product;
import com.wchs.repository.CategoryRepository;
import com.wchs.repository.ProductRepository;
import com.wchs.util.BackEndResponse;
import com.wchs.util.MessageCode;
import com.wchs.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Islam on 3/19/2016.
 */
@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public BackEndResponse list() {
        BackEndResponse backEndResponse = new BackEndResponse();
        backEndResponse.setObject(categoryRepository.list());
        backEndResponse.setResultStatus(ResultStatus.SUCCESS);
        backEndResponse.setMessageCode(MessageCode.SUCCESS);
        return backEndResponse;
    }
    public ResultStatus updateCategories() {
        return categoryRepository.update();
    }

    public BackEndResponse save(Category category) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = categoryRepository.save(category);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(category);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse delete(List<Category> categories) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = ResultStatus.SUCCESS;
        for (Category category : categories) {
            System.out.println(category.getName());
            resultStatus = categoryRepository.delete(category);
        }
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse update(Category category) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = categoryRepository.update(category);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(category);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }


    public Double getSumOfCapital() {
        return categoryRepository.getSumOfCapital();
    }

    public Double getSumOfProfit() {
        return categoryRepository.getSumOfProfit();
    }
}
