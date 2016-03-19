package com.wchs.restservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wchs.model.Borrow;
import com.wchs.service.BorrowService;
import com.wchs.service.CustomerService;
import com.wchs.util.BackEndResponse;
import com.wchs.util.MessageCode;
import com.wchs.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by Islam on 3/19/2016.
 */
@Path("/borrow")
@Service("borrowWebService")
public class BorrowWebService {
    @Autowired
    BorrowService borrowService;

    @GET
    @Path("/list")
    public String list() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            return gson.toJson(borrowService.list());
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gson.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/save")
    public String save(@RequestBody String borrowJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Borrow borrow = gsonRequest.fromJson(borrowJson, Borrow.class);
            return gsonResponse.toJson(borrowService.save(borrow));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/update")
    public String update(@RequestBody String borrowJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Borrow borrow = gsonRequest.fromJson(borrowJson, Borrow.class);
            return gsonResponse.toJson(borrowService.update(borrow));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/delete")
    public String delete(@RequestBody String borrowJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Borrow borrow = gsonRequest.fromJson(borrowJson, Borrow.class);
            return gsonResponse.toJson(borrowService.delete(borrow));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }
}
