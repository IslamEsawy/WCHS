package com.wchs.service;

import com.wchs.model.Customer;
import com.wchs.model.Transaction;
import com.wchs.repository.CustomerRepository;
import com.wchs.repository.TransactionRepository;
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
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;


    public BackEndResponse list() {
        BackEndResponse backEndResponse = new BackEndResponse();
        backEndResponse.setObject(transactionRepository.list());
        backEndResponse.setResultStatus(ResultStatus.SUCCESS);
        backEndResponse.setMessageCode(MessageCode.SUCCESS);
        return backEndResponse;
    }

    public BackEndResponse list(Integer id) {
        BackEndResponse backEndResponse = new BackEndResponse();
        backEndResponse.setObject(transactionRepository.list(id));
        backEndResponse.setResultStatus(ResultStatus.SUCCESS);
        backEndResponse.setMessageCode(MessageCode.SUCCESS);
        return backEndResponse;
    }

    public BackEndResponse save(List<Transaction> transactions) {
        BackEndResponse backEndResponse = new BackEndResponse();
        Customer cus = transactions.get(0).getCpid().getCustomer();
        //cus.setcProducts(transactions);
        customerService.update(cus);
        productService.update(transactions);
        ResultStatus resultStatus = transactionRepository.save(transactions);

        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(transactions);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse delete(Transaction transaction) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = transactionRepository.delete(transaction);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(transaction);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse update(Transaction transaction) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = transactionRepository.update(transaction);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(transaction);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }
}
