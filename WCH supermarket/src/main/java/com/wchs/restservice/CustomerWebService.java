package com.wchs.restservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wchs.model.Customer;
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
@Path("/customer")
@Service("customerWebService")
public class CustomerWebService {

    @Autowired
    CustomerService customerService;

    @GET
    @Path("/list")
    public String list() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            return gson.toJson(customerService.list());
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gson.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/save")
    public String save(@RequestBody String customerJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Customer customer = gsonRequest.fromJson(customerJson, Customer.class);
            return gsonResponse.toJson(customerService.save(customer));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/update")
    public String update(@RequestBody String customerJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Customer customer = gsonRequest.fromJson(customerJson, Customer.class);
            return gsonResponse.toJson(customerService.update(customer));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/delete")
    public String delete(@RequestBody String customerJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Customer customer = gsonRequest.fromJson(customerJson, Customer.class);
            return gsonResponse.toJson(customerService.delete(customer));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }
}
