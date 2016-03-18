package com.wchs.service;

import com.wchs.model.Miscellaneous;
import com.wchs.repository.MiscellaneousRepository;
import com.wchs.util.BackEndResponse;
import com.wchs.util.MessageCode;
import com.wchs.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Islam on 3/18/2016.
 */
@Service
@Transactional
public class MiscellaneousService {

    @Autowired
    private MiscellaneousRepository miscellaneousRepository;

    public BackEndResponse list() {
        BackEndResponse backEndResponse = new BackEndResponse();
        backEndResponse.setObject(miscellaneousRepository.list());
        backEndResponse.setResultStatus(ResultStatus.SUCCESS);
        backEndResponse.setMessageCode(MessageCode.SUCCESS);
        return backEndResponse;
    }

    public BackEndResponse save(Miscellaneous miscellaneous) {
        BackEndResponse backEndResponse = new BackEndResponse();
        ResultStatus resultStatus = miscellaneousRepository.save(miscellaneous);
        if (ResultStatus.SUCCESS.equals(resultStatus)) {
            backEndResponse.setMessageCode(MessageCode.SUCCESS);
            backEndResponse.setObject(miscellaneous);
        } else {
            backEndResponse.setMessageCode(MessageCode.ERROR);
        }
        backEndResponse.setResultStatus(resultStatus);
        return backEndResponse;
    }
}
