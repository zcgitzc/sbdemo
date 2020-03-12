package com.zark.sbproject.boot.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

public class HttpClientUtil {


    public static String doPostJson(String url, Map<String, String> mapdata) throws Exception {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httpPost = new HttpPost(url);
        try {
            // 设置提交方式
//            httpPost.addHeader("Content-type", "application/json");
//
//            httpPost.addHeader("Set-Cookie","yd_cookie=e795e807-1d1b-4ec57184050ca8009ea51bc2f1218724978a;PHPSESSID=29ko5o1q2l1jf2kmoct8cuv222");


            // 添加参数
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (mapdata.size() != 0) {
                // 将mapdata中的key存在set集合中，通过迭代器取出所有的key，再获取每一个键对应的值
                Set keySet = mapdata.keySet();
                Iterator it = keySet.iterator();
                while (it.hasNext()) {
                    String k = (String) it.next();// key
                    String v = mapdata.get(k);// value
                    nameValuePairs.add(new BasicNameValuePair(k, v));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            System.out.println("nameValuePairs:" + nameValuePairs);
            // 执行http请求
            response = httpClient.execute(httpPost);
            // 获得http响应体
            HttpEntity entity = response.getEntity();
            System.out.println("entity:" + entity);
            if (entity != null) {
                // 响应的结果
                String content = EntityUtils.toString(entity, "UTF-8");
                System.out.println("content" + content);
                return content;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "获取数据错误";
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> mapData = new HashMap<>(2);
        mapData.put("account", "1234");
        mapData.put("password", "1234");
        String s = HttpClientUtil.doPostJson("http://qw.kkmmyz.fun/home/index/login.html", mapData);
        System.out.println(s);
    }
}