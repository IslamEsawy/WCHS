package com.wchs.service;

import com.wchs.model.Borrow;
import com.wchs.util.BackEndResponse;
import com.wchs.util.MessageCode;
import com.wchs.util.ResultStatus;

/**
 * Created by Islam on 3/19/2016.
 */
public interface Service<T> {
    BackEndResponse list();
    BackEndResponse save(T t);
    BackEndResponse delete(T t) ;
    BackEndResponse update(T t) ;
}
