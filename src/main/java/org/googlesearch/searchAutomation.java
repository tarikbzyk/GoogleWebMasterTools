package org.googlesearch;

import com.sun.net.httpserver.HttpsParameters;
import javafx.css.CssMetaData;
import lombok.SneakyThrows;
import okhttp3.internal.http.HttpMethod;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import pageObjects.ConsolePage;
import pageObjects.LoginPage;
import resources.base;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class searchAutomation extends base {

    public static WebDriver driver;
    public static List<urlFormat> urlMList = new ArrayList<>();
    public static HttpClient client = HttpClientBuilder.create().build();




    public static void main(String[] args) throws InterruptedException, IOException {


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
        List<WebElement> hataTuru = cp.getKapsamListe();


        for(int i=0;i<hataTuru.size();i++){

            loop500(i,hataTuru,cp,jse,httpLists);
        }
        System.setProperty("https.protocols", "TLSv1.1");
        httpStatusCalisma(urlMList,httpLists);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet S200 = workbook.createSheet( "200");
        XSSFSheet S301 = workbook.createSheet( "301");
        XSSFSheet S404 = workbook.createSheet( "404");
        XSSFSheet S500 = workbook.createSheet( "500");
        XSSFSheet S501 = workbook.createSheet( "501");
        XSSFSheet S502 = workbook.createSheet( "502");
        XSSFSheet S_Other = workbook.createSheet( "Other");

        dosyaIsleme(httpLists.list200,S200);
        dosyaIsleme(httpLists.list301,S301);
        dosyaIsleme(httpLists.list404,S404);
        dosyaIsleme(httpLists.list500,S500);
        dosyaIsleme(httpLists.list501,S501);
        dosyaIsleme(httpLists.list502,S502);
        dosyaIsleme(httpLists.other,S_Other);

        yazdır(workbook,"touch");

    }

    public static int SatirSayisiBelirle(String urlSayisi){

        int butonsatirsayisi =0;
        int SatirSayisi = Integer.parseInt(urlSayisi);

        if(SatirSayisi>=500){
            butonsatirsayisi=500;
        }
        else if(SatirSayisi>=250 && SatirSayisi<500){
            butonsatirsayisi=250;
        }
        else if(SatirSayisi>=100 && SatirSayisi<250){
            butonsatirsayisi=100;
        }
        else if(SatirSayisi>=50 && SatirSayisi<100){
            butonsatirsayisi=50;
        }
        else if(SatirSayisi>10 && SatirSayisi<50){
            butonsatirsayisi=25;
        }
        else if(SatirSayisi<=10 && SatirSayisi>5){
            butonsatirsayisi=10;
        }
        else if (SatirSayisi<=5 && SatirSayisi>0){
            butonsatirsayisi=5;
        }
        else{
            butonsatirsayisi=0;
        }

        return butonsatirsayisi;
    }

    public static void loop(int index , List<WebElement> hataTuru , ConsolePage cp , JavascriptExecutor jse, httpLists httpLists) throws IOException {
        int listeSayısı = SatirSayisiBelirle(hataTuru.get(index).findElement(By.xpath(".//td[5]")).getAttribute("data-numeric-value"));


        if(listeSayısı>0) {
            System.out.println(listeSayısı+" içerik sayfda listelenecek..");
            hataTuru.get(index).click();
            jse.executeScript("arguments[0].scrollIntoView();", cp.getDropSayfaBasiSatır());
            cp.getDropSayfaBasiSatır().click();
            cp.getButonListeAdedi(Integer.toString(listeSayısı)).click();
            listeleme(cp,httpLists);
            System.out.println(urlMList.size());
            driver.navigate().back();
        }

    }

    public static void loop500(int index , List<WebElement> hataTuru , ConsolePage cp , JavascriptExecutor jse, httpLists httpLists) throws IOException {
        int listeSayısı = SatirSayisiBelirle(hataTuru.get(index).findElement(By.xpath(".//td[5]")).getAttribute("data-numeric-value"));
        String hataAdı = hataTuru.get(index).findElement(By.xpath(".//td[2]")).getAttribute("data-string-value");
        if(hataAdı.contains("(5xx)")) {

            if (listeSayısı > 0) {
                System.out.println(listeSayısı + " içerik sayfda listelenecek..");
                hataTuru.get(index).click();
                jse.executeScript("arguments[0].scrollIntoView();", cp.getDropSayfaBasiSatır());
                cp.getDropSayfaBasiSatır().click();
                cp.getButonListeAdedi(Integer.toString(listeSayısı)).click();
                listeleme(cp, httpLists);
                System.out.println(urlMList.size());
                driver.navigate().back();
            }
        }

    }

    public static void listeleme(ConsolePage cp , httpLists httpList) throws IOException{


        List<WebElement> element = cp.getUrlSatir();
        System.out.println(element.size()+" url listelendi");
        String url , sontarama;


        for (int i = 0; i < element.size(); i++) {

            url = element.get(i).findElement(By.xpath(".//td[1]/span/span")).getAttribute("title");
            sontarama = element.get(i).findElement(By.xpath(".//td[2]/span/span")).getAttribute("innerHTML");
            sontarama = sontarama.replace("<div class=\"qUFtbf\"></div>","");
            urlFormat objectUrl = new urlFormat(url, sontarama);
            urlMList.add(objectUrl);
        }
    }

    public static void httpStatusCalisma(List<urlFormat> a, httpLists httpList) throws IOException{
        int httpStatus = 0;
        for (int i=0;i<a.size();i++){
            httpStatus = responseCode(a.get(i).getUrlAdres());

            if (httpStatus == 200){
                httpList.list200.add(a.get(i));
            }
            else if(httpStatus == 404){
                httpList.list404.add(a.get(i));
            }
            else if(httpStatus == 500){
                httpList.list500.add(a.get(i));
            }
            else if(httpStatus == 501){
                httpList.list501.add(a.get(i));
            }
            else if(httpStatus == 502){
                httpList.list502.add(a.get(i));
            }
            else if(httpStatus == 301){
                httpList.list301.add(a.get(i));
            }
            else{
                httpList.other.add(a.get(i));
            }
        }

    }

    public static int responseCode(String url) throws IOException {
        HttpResponse response = null;
        try {

            //HttpClient client = HttpClientBuilder.create().build();
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

    public static void dosyaIsleme(List<urlFormat> a, XSSFSheet spreadsheetC) {

        int rowid = 0;
        XSSFRow rowC;
        //spreadsheetC.autoSizeColumn(100000);
        for (int i = 0; i < a.size(); i++) {
            rowC = spreadsheetC.createRow(rowid);
            Cell cell = rowC.createCell(0);
            cell.setCellValue(a.get(i).getUrlAdres());
            cell = rowC.createCell(1);
            cell.setCellValue(a.get(i).getSonTarama());
            rowid++;
        }


    }

    public static void yazdır(XSSFWorkbook workbook, String dosyaAd) throws IOException {
        try {
            FileOutputStream out = new FileOutputStream(
                    new File("C:\\Users\\tarik.bozyak\\Desktop\\webmastertools\\"+dosyaAd+".xlsx"));
            workbook.write(out);
            out.close();
            System.out.println(dosyaAd+".xlsx written successfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
