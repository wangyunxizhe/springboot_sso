package com.yuan.controller.sameFarther.demo1.x.com.utlis;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wangy on 2018/11/22.
 */
@Component("sameFartherDemo1Tool")
public class Demo1Tool {

    public String doGet(String url, String cookieName, String cookieValue) {
        StringBuffer sb = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        try {
            URL usrls = new URL(url + "?cookieName" + cookieName + "&cookieValue" + cookieValue);
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
