package com.mn.socketp1.component.http;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Type;

/**
 * AUTHOR Rexxar
 * DATE 19/11/13
 * DESC
 */
@Component
@Slf4j
public class HttpClient {

    private CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public static final String NO_RESPONSE = "未获取到";

    public static final int MAXRET = 3;  //最大重发次数

    @Resource
    private Gson gson;

    public <T> T executeRequest(HttpRequestBase httpRequest, Type typeOfT) {
        T result = null;
//        String responseText = "未获取到";
        String responseText = NO_RESPONSE;
        try {
            HttpResponse response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
                result = gson.fromJson(responseText, typeOfT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("HTTP请求失败,ResponseEntity=" + responseText);
        }
        return result;
    }

    String executeRequest(HttpRequestBase httpRequest) {
//        String responseText = "未获取到";
        String responseText = NO_RESPONSE;
        try {
            HttpResponse response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("HTTP请求失败,ResponseEntity=" + responseText);
        }
        return responseText;
    }

    HttpPost getPostRequest(String url) {
        HttpPost post = new HttpPost(url);
        post.setHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

        return post;
    }

    public HttpGet getGetRequest(String url) {
        HttpGet get = new HttpGet(url);
        get.setHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
        return get;
    }
}
