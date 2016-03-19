package com.wchs.restservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wchs.model.Income;
import com.wchs.service.IncomeService;
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
@Path("/income")
@Service("incomeWebService")
public class IncomeWebService {
    @Autowired
    IncomeService incomeService;

    @GET
    @Path("/list")
    public String list() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            return gson.toJson(incomeService.list());
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gson.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/save")
    public String save(@RequestBody String incomeJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Income income = gsonRequest.fromJson(incomeJson, Income.class);
            return gsonResponse.toJson(incomeService.save(income));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/update")
    public String update(@RequestBody String incomeJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Income income = gsonRequest.fromJson(incomeJson, Income.class);
            return gsonResponse.toJson(incomeService.update(income));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }

    @POST
    @Path("/delete")
    public String delete(@RequestBody String incomeJson) {
        Gson gsonRequest = new Gson();
        Gson gsonResponse = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            Income income = gsonRequest.fromJson(incomeJson, Income.class);
            return gsonResponse.toJson(incomeService.delete(income));
        } catch (Exception e) {
            BackEndResponse backEndResponse = new BackEndResponse();
            backEndResponse.setResultStatus(ResultStatus.FAILED);
            backEndResponse.setMessageCode(MessageCode.ERROR);
            return gsonResponse.toJson(backEndResponse);
        }
    }
}
