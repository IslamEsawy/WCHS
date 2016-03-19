package com.wchs;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Islam on 3/19/2016.
 */
public class Test {
    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        Test http = new Test();

        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();


        System.out.println("\nTesting 2 - Send Http POST request");
        http.sendPost();


    }

    // HTTP GET request
    private void sendGet() throws Exception {

        String url = "http://localhost:8080/WCHS/customer/list";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

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

        System.out.println(result.toString());

    }

    // HTTP POST request
    private void sendPost() throws Exception {

        String url = "http://localhost:8080/WCHS/customer/update";

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(url);

        JSONObject object = new JSONObject();
        try {
            object.put("cid", 1);
            object.put("name", "Islam");
            object.put("totalCash", 10.5);
            object.put("moneyToReturn", 20.75);

        } catch (Exception ex) {

        }

        String message = object.toString();


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

        System.out.println(result.toString());

    }
}
