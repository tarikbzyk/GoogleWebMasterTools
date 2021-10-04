package org.googlesearch;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class App {

    public static Properties prop;

    public static void main(String[] args) throws InterruptedException, IOException {


        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tarik.bozyak\\selenium\\chromedriver.exe");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);


        WebDriver driver = new ChromeDriver(options);
        driver.get("https://search.google.com/search-console/about");
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//div[@class='ThQnOe uaHa6c JcdbFb']/div[@class='PXzS6e']/div[@class='eZynkc']/div[@class='KXgdRe qOvw5c']/div[2]/div")).click();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("tarik.bozyak@sabah.com.tr");
        driver.findElement(By.xpath("//div[@class='dG5hZc']/div[@class='qhFLie']/div")).click();
        WebElement passwordElement = driver.findElement(By.xpath("//input[@name='password']"));
        passwordElement.click();
        passwordElement.clear();
        String password = getStreaming().getProperty("password");
        passwordElement.sendKeys(password);
        driver.findElement(By.xpath("//div[@class='dG5hZc']/div[@class='qhFLie']/div")).click();


        driver.findElement(By.xpath("//nav[@class='j2F0y']/div[4]/div/div/div[@class='V8vvZb']/div/div/span/div[@jsname='cQYSPc']")).click();

        /* sayfasa sayısına göre sıralama */
        //driver.findElement(By.xpath("//div[@id='yDmH0d']/c-wiz[@class='zQTmif SSPGKf eejsDc']/span/c-wiz/c-wiz/div/div[@class='OoO4Vb']/div[@class='shSP']/div/div/c-wiz/div/span/div/div[@jsname='FOLXuf']/div[@class='CtOYUe I4chsf VJARVc XIlx4d']/table/thead/tr/th[@data-type='number']")).click();

        //String kapsamAdres = "//div[@class='CtOYUe I4chsf VJARVc XIlx4d']/table/tbody/tr[@class='nJ0sOc wNFy3d  ptEsvc ']";
        String kapsamAdres = "//div[@id='yDmH0d']/c-wiz[@class='zQTmif SSPGKf eejsDc']/span/c-wiz/c-wiz/div/div[@class='OoO4Vb']/div[@class='shSP']/div/div/c-wiz/div/span/div/div[@jsname='FOLXuf']/div[@class='CtOYUe I4chsf VJARVc XIlx4d']/table/tbody/tr[@class='nJ0sOc wNFy3d  ptEsvc ']";
        List<WebElement> kapsamListe = driver.findElements(By.xpath(kapsamAdres));
        System.out.println(kapsamListe.size());


        /*
        for(int i=0;i<kapsamListe.size();i++){
            driver.findElement(By.xpath(kapsamAdres+"/["+(i+1)+"]/td[5]"));


        }

         */

        kapsamListe.get(1).click();

        WebElement listeAdet = driver.findElement(By.xpath("//div[@class='gqI7uf WR1Me']/c-wiz[@jsrenderer='te3aK']/div/span/div/div[@class='VgjuZe hYFhF']/div/div/span[1]/span/div[@role='listbox']/div[@jsname='LgbsSe']"));

        /*sayfada listelencek içerik sayısının seçildiği dropdown menuye kadar sayfayı aşağı indirir. */
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", listeAdet);
        //sayfa adeti seçilecek liste açılır
        listeAdet.click();
        //listeden 500 tıklanır
        driver.findElement(By.xpath("//div[@class='gqI7uf WR1Me']/c-wiz[@jsrenderer='te3aK']/div/span/div/div[@class='VgjuZe hYFhF']/div/div/span[1]/span/div[@role='listbox']/div[@class='OA0qNb ncFHed']/div[@data-value='500']")).click();

        //açılan listeden seçilen elemanın metni getirilir.

        String adresXpath = "//div[@id='yDmH0d']/c-wiz[3]/c-wiz/div/div[@class='OoO4Vb']/span/div/div[@class='y3IDJd rFZTte Fx3kmc']/span/div[@class='shSP']/div/div/c-wiz[@jsrenderer='te3aK']/div/span/div/div[@jsname='FOLXuf']/div[@class='CtOYUe I4chsf VJARVc']/table/tbody/tr";
        List<WebElement> listOfUrl = driver.findElements(By.xpath(adresXpath));

        System.out.println("Toplam link sayısı : " + listOfUrl.size());


        List<urlFormat> objectListe = listeleme(driver, adresXpath);


        /*
        System.out.println("Url : " + objectListe.get(3).getUrlAdres());
        System.out.println("code : " + objectListe.get(3).getHttpStatusCode());

         */

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet( "Tag Listesi");
        XSSFSheet spreadsheet400 = workbook.createSheet( "400 Listesi");

        dosyaIsleme(objectListe,spreadsheet);
        yazdır(workbook,"touch");

    }

    public static List<urlFormat> listeleme(WebDriver driver, String adres) throws IOException {

        String urlKontrol;
        List<WebElement> listeWeb = driver.findElements(By.xpath(adres));
        List<urlFormat> urlMList = new ArrayList<>();

        for (int i = 1; i < 100; i++) {

            urlKontrol = driver.findElement(By.xpath(adres + "[" + i + "]/td[1]/span/span")).getAttribute("title");
            urlFormat objectUrl = new urlFormat(urlKontrol,  "");
            urlMList.add(objectUrl);
        }

        return urlMList;
    }

    public static Properties getStreaming() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\tarik.bozyak\\IdeaProjects\\webmastertools\\webmastertools\\src\\main\\java\\resources\\data.properties");
        prop.load(fis);
        return prop;

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
            cell.setCellValue(a.get(i).getHttpStatusCode());
            rowid++;
        }


    }

    public static void yazdır(XSSFWorkbook workbook, String dosyaAd) throws IOException {
        try {
            FileOutputStream out = new FileOutputStream(
                    new File("C:\\Users\\tarik.bozyak\\IdeaProjects\\webmastertools\\webmastertools\\"+dosyaAd+".xlsx"));
            workbook.write(out);
            out.close();
            System.out.println(dosyaAd+".xlsx written successfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}