package org.googlesearch;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.RedirectLocations;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.ConsolePage;
import pageObjects.LoginPage;
import resources.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class deneme {

    public static WebDriver driver;

    public static void main(String[] args) throws IOException, InterruptedException {


        /*
            deneme d1 = new deneme();

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("https://www.sabah.com.tr/saglik/2021/08/10/zeytinyaginin-faydalari-nelerdir-zeytinyagi-nelere-iyi-gelir");
            //HttpGet req = d1.configureHttpGet(request,5000L);

            HttpResponse response = client.execute(request);

            int a = response.getStatusLine().getStatusCode();
            System.out.println(a);

         */


        base con = new base();
        driver =con.initializeDriver();
        driver.get(con.prop.getProperty("url"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        httpLists httpLists = new httpLists();


        /*Google login adımları*/
        LoginPage lp = new LoginPage(driver);
        lp.getBtnSimdiBasla().click();
        lp.getEditBoxEmail().sendKeys(con.prop.getProperty("email"));
        lp.getBtnEmailNext().click();
        lp.getEditBoxPassword().click();
        lp.getEditBoxPassword().sendKeys(con.prop.getProperty("password"));
        lp.getBtnPasswordNext().click();

        /*Google Search Console işlemleri*/
        ConsolePage cp = new ConsolePage(driver);
        String marka = cp.getDropBrand().findElement(By.xpath(".//div/div[2]/div[1]")).getAttribute("data-initialvalue");

        if(!(marka.equals(con.prop.getProperty("proje")))){
            //marka data.properties'deki proje'de yazan url ile aynı değilse "cp.getDropBrand().click();" komutu ile drop menu açılacak.
            cp.getDropBrand().click();
            cp.getDropMenuBrand(con.prop.getProperty("proje")).click();
            Thread.sleep(3000L);
            //açılan drop menuden data.properties dosyasında ki url bulunup tıklanıcak...
        }

        cp.getBtnKapsam().click();

        /*
        List<WebElement> ls = new ArrayList<>();
        ls=cp.getDropMenuBrand();
        System.out.println(ls.size());
        System.out.println(ls.get(0).getAttribute("title"));
        System.out.println(ls.get(1).getAttribute("title"));
        System.out.println(ls.get(2).getAttribute("title"));

         */

    }



    protected HttpGet configureHttpGet(HttpGet httpGet, long timeout) {
        httpGet.setConfig(RequestConfig.custom()
                .setConnectTimeout((int) timeout)
                .setSocketTimeout((int) timeout)
                .setConnectionRequestTimeout((int) timeout)
                .setRedirectsEnabled(true)
                .setCircularRedirectsAllowed(true)
                .setMaxRedirects(10)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build()
        );
        return httpGet;

    }

}
