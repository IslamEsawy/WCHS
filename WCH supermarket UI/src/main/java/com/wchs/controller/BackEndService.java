package com.wchs.controller;

import com.google.gson.Gson;
import com.wchs.model.Customer;
import com.wchs.util.BackEndResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Islam on 3/19/2016.
 */
public class BackEndService {
    private static BackEndService backEndService;

    public static synchronized BackEndService getInstance(){
        if (backEndService == null)
            return backEndService=new BackEndService();
        return backEndService;
    }

    // HTTP GET request
    public BackEndResponse sendGet(String url) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        // add request header
        request.addHeader("User-Agent", "WCHS");
        HttpResponse response = client.execute(request);
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return backEndService.parseJSON(result.toString());
    }

    // HTTP POST request
    public BackEndResponse sendPost(String message, String url) throws Exception {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
/*        JSONObject object = new JSONObject();
        try {
            object.put("name", "ahmed");
            object.put("totalCash", 10.5);
            object.put("moneyToReturn", 20.75);

        } catch (Exception ex) {

        }
        String message = object.toString();*/
        post.setEntity(new StringEntity(message, "UTF8"));
        post.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(post);
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return backEndService.parseJSON(result.toString());

    }

    public BackEndResponse parseJSON(String json){
        Gson gsonResponse = new Gson();
        return gsonResponse.fromJson(json, BackEndResponse.class);
    }


}
