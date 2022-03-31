package com.yuan.controller.cross.www.a.com.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by wangy on 2018/11/22.
 */
@Component("crossDemo1Tool")
public class Demo1Tool {

    public String doGet(String url, Map<String, String> map) {
        StringBuffer sb = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        try {
            StringBuffer temp = new StringBuffer(url).append("?");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                temp.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            //去掉最后一个“&”
            url = temp.substring(0, temp.length() - 1);
            URL usrls = new URL(url);
            httpURLConnection = (HttpURLConnection) usrls.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return sb.toString();
    }

}
