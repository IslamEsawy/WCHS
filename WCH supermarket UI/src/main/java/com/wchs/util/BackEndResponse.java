package com.wchs.util;

import com.google.gson.annotations.Expose;

/**
 * Created by Islam on 3/18/2016.
 */
public class BackEndResponse {

    @Expose
    MessageCode messageCode;

    @Expose
    ResultStatus resultStatus;

    @Expose
    Object object;

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(MessageCode messageCode) {
        this.messageCode = messageCode;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
