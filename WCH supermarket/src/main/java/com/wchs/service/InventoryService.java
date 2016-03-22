package com.wchs.service;

import com.google.gson.annotations.Expose;
import com.wchs.model.Category;
import com.wchs.model.Inventory;
import com.wchs.model.Miscellaneous;
import com.wchs.repository.CategoryRepository;
import com.wchs.repository.InventoryRepository;
import com.wchs.repository.MiscellaneousRepository;
import com.wchs.repository.ProductRepository;
import com.wchs.util.BackEndResponse;
import com.wchs.util.MessageCode;
import com.wchs.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Islam on 3/19/2016.
 */
@org.springframework.stereotype.Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MiscellaneousService miscellaneousService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BorrowService borrowService;

    public BackEndResponse list() {
        BackEndResponse backEndResponse = new BackEndResponse();
        backEndResponse.setObject(inventoryRepository.list());
        backEndResponse.setResultStatus(ResultStatus.SUCCESS);
        backEndResponse.setMessageCode(MessageCode.SUCCESS);
        return backEndResponse;
    }


    public BackEndResponse reset() {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = inventoryRepository.reset();
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }


    public BackEndResponse doInvetnry() {
        BackEndResponse backEndResponse = new BackEndResponse();
        if (ResultStatus.SUCCESS.equals(categoryService.updateCategories())) {
            String[] months = {"January",
                    "February",
                    "March",
                    "April",
                    "May",
                    "June",
                    "July",
                    "August",
                    "September",
                    "October",
                    "November",
                    "December"};
            String month = months[LocalDate.now().getMonthValue() - 1];
            Double totalMisc = miscellaneousService.getSumofMisc();
            Double totalBorrow = borrowService.getSumofBorrow();
            if (totalMisc == null)
                totalMisc = 0.0;
            if (totalBorrow == null)
                totalBorrow = 0.0;
            Double totalCategoriesCapital = categoryService.getSumOfCapital();
            Double totalCategoriesProfit = categoryService.getSumOfProfit();
            Double restOfGoodsCapital = productService.getRestOfGoodsCapital();
            Double restOfGoodsProfit = productService.getRestOfGoodsProfit();
            System.out.println(restOfGoodsCapital);
            System.out.println(restOfGoodsProfit);
            Inventory inventory = new Inventory(month, restOfGoodsCapital, restOfGoodsProfit, totalBorrow, totalMisc,totalCategoriesCapital,totalCategoriesProfit);
            backEndResponse.setObject(inventory);
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setResultStatus(ResultStatus.SUCCESS);
        } else {
            backEndResponse.setMessageCode(MessageCode.ERROR);
            backEndResponse.setResultStatus(ResultStatus.FAILED);
        }
        return backEndResponse;
    }

    public BackEndResponse save(Inventory inventory) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = inventoryRepository.save(inventory);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(inventory);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }
    
}
