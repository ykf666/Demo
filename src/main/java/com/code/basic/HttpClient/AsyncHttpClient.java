package com.code.basic.HttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by yankefei on 2018/11/21.
 */
public class AsyncHttpClient {

    public static void main(String[] args) {
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();

        HttpPost request = new HttpPost("https://www.baidu.com");

        httpClient.execute(request, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                HttpEntity entity = httpResponse.getEntity();
                try {
                    Thread.sleep(2000);
                    StatusLine statusLine = httpResponse.getStatusLine();
                    int statusCode = statusLine.getStatusCode();
                    System.out.println("子线程:" + Thread.currentThread().getName() + "异步请求返回状态码:" + statusCode);
                    if (statusCode == HttpURLConnection.HTTP_OK) {
                        String body = EntityUtils.toString(entity, "UTF-8");
                        System.out.println("子线程:" + Thread.currentThread().getName() + "异步请求返回:" + body);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e) {
                System.out.println("异步请求失败！");
            }

            @Override
            public void cancelled() {
                System.out.println("异步请求取消！");
            }
        });

        try {
            System.out.println("主线程:" + Thread.currentThread().getName() + "继续执行...");
            for (int i = 1; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
            httpClient.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
