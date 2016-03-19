package com.wchs.restservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wchs.model.Transaction;
import com.wchs.service.TransactionService;
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
@Path("/transaction")
@Service("transactionWebService")
public class TransactionWebService {
    @Autowired
    TransactionService transactionService;

    @GET
    @Path("/list")
    public String list() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            return gson.toJson(transactionService.list());
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gson.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/save")
    public String save(@RequestBody String transactionJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Transaction transaction = gsonRequest.fromJson(transactionJson, Transaction.class);
            return gsonResponse.toJson(transactionService.save(transaction));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/update")
    public String update(@RequestBody String transactionJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Transaction transaction = gsonRequest.fromJson(transactionJson, Transaction.class);
            return gsonResponse.toJson(transactionService.update(transaction));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/delete")
    public String delete(@RequestBody String transactionJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Transaction transaction = gsonRequest.fromJson(transactionJson, Transaction.class);
            return gsonResponse.toJson(transactionService.delete(transaction));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }
}
