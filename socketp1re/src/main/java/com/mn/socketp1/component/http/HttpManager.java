package com.mn.socketp1.component.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/4/2 14:12
 * DESC
 */
@Component
@Slf4j
public class HttpManager {
    @Resource
    private HttpClient httpClient /*= new HttpClient()*/;


    public String dataTran(String key1, String value1, String key2, String value2, String url) {
        String responseText = HttpClient.NO_RESPONSE;
        try {
//            HttpPost post = httpClient.getPostRequest(url);
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/x-www-form-urlencoded");
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair(key1, value1));
            list.add(new BasicNameValuePair(key2, value2));
            StringEntity stringEntity = new UrlEncodedFormEntity(list, "utf-8");
            post.setEntity(stringEntity);

            responseText = httpClient.executeRequest(post);
            log.info("响应：" + responseText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseText;
    }

    public String queryDeviceList(String key1, String value1, String url) {
        try {
//            HttpGet get = httpClient.getGetRequest(url);
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair(key1, value1));
            String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
            HttpGet get = new HttpGet(url + "?" + params);
            return httpClient.executeRequest(get);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
