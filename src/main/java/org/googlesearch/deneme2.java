package org.googlesearch;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import resources.base;

import java.io.IOException;

public class deneme2 {

    public static WebDriver driver;

    public static void main(String[] args) throws IOException {
        base con = new base();
        System.out.println(responseCode("https://www.ahaber.com.tr/gundem/2020/09/19/chpli-didim-belediyesinde-kirli-iliskiler-yumagindan-50-milyonluk-servet-cikti"));




    }

    public static int responseCode(String url) throws IOException {
        HttpResponse response = null;
        try {

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            response = client.execute(request);


        } catch (IllegalArgumentException e) {
            return 0;
        }
        catch (ClientProtocolException e){
            return 301;
        }

        return response.getStatusLine().getStatusCode();


    }
}
