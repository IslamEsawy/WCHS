package com.wchs.service;

import com.wchs.model.Income;
import com.wchs.repository.IncomeRepository;
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
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public BackEndResponse list() {
        BackEndResponse backEndResponse = new BackEndResponse();
        backEndResponse.setObject(incomeRepository.list());
        backEndResponse.setResultStatus(ResultStatus.SUCCESS);
        backEndResponse.setMessageCode(MessageCode.SUCCESS);
        return backEndResponse;
    }


    public BackEndResponse save(Income income) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = incomeRepository.save(income);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(income);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse delete(Income income) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = incomeRepository.delete(income);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(income);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }

    public BackEndResponse update(Income income) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = incomeRepository.update(income);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(income);
        } else
            backEndResponse.setMessageCode(MessageCode.ERROR);
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }
}
