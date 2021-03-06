package com.wchs.service;

import com.wchs.model.Borrow;
import com.wchs.model.Borrow;
import com.wchs.repository.BorrowRepository;
import com.wchs.util.BackEndResponse;
import com.wchs.util.MessageCode;
import com.wchs.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;

/**
 * Created by Islam on 3/19/2016.
 */
@Service
@Transactional
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    public BackEndResponse list() {
        BackEndResponse backEndResponse = new BackEndResponse();
        backEndResponse.setObject(borrowRepository.list());
        backEndResponse.setResultStatus(ResultStatus.SUCCESS);
        backEndResponse.setMessageCode(MessageCode.SUCCESS);
        return backEndResponse;
    }


    public BackEndResponse save(Borrow borrow) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = borrowRepository.save(borrow);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(borrow);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse delete(Borrow borrow) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = borrowRepository.delete(borrow);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(borrow);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse update(Borrow borrow) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = borrowRepository.update(borrow);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(borrow);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }


    public BackEndResponse makePaid(String[] id) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = ResultStatus.SUCCESS;
        for (String id1 : id){
            System.out.println(id1);
            resultStatus = borrowRepository.makePaid(id1);
        }
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public Double getSumofBorrow() {
        return borrowRepository.getSum();
    }
}
