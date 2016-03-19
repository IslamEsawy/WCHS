package com.wchs.service;

import com.wchs.model.Customer;
import com.wchs.repository.CustomerRepository;
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
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public BackEndResponse list() {
        BackEndResponse backEndResponse = new BackEndResponse();
        backEndResponse.setObject(customerRepository.list());
        backEndResponse.setResultStatus(ResultStatus.SUCCESS);
        backEndResponse.setMessageCode(MessageCode.SUCCESS);
        return backEndResponse;
    }


    public BackEndResponse save(Customer customer) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = customerRepository.save(customer);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(customer);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse delete(Customer customer) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = customerRepository.delete(customer);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(customer);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse update(Customer customer) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = customerRepository.update(customer);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(customer);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }}
