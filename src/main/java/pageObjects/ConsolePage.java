package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ConsolePage {


    public WebDriver driver;
    String value;

    By dropBrand = By.xpath("//header/div[@role='navigation']/div[@role='menu']/div[1]/c-wiz[1]/div[2]/div[@class='cK8COe']/c-wiz[1]/div[1]");

    By dropBrandMenu = By.xpath("//header/div[@role='navigation']/div[@role='menu']/div[1]/c-wiz[1]/div[2]/div[@class='cK8COe']/c-wiz[1]/div[1]/div/div[2]/div[@class=' tWfTvb']/div/div[1]/div[contains(@title,'https')]");

    By btnKapsam =By.xpath("//nav[@class='j2F0y']/div[4]/div/div/div[@class='V8vvZb']/div/div/span/div[@jsname='cQYSPc']");

    By kapsamListe = By.xpath("//div[@id='yDmH0d']/c-wiz[@class='zQTmif SSPGKf eejsDc']/span/c-wiz/c-wiz/div/div[@class='OoO4Vb']/div[@class='shSP']/div/div/c-wiz/div/span/div/div[@jsname='FOLXuf']/div[@class='CtOYUe I4chsf VJARVc XIlx4d']/table/tbody/tr[@class='nJ0sOc wNFy3d  ptEsvc ']");

    By dropMainSayfaBasiSatir = By.xpath("//body/div[@id='yDmH0d']/c-wiz[@class='zQTmif SSPGKf eejsDc']/span/c-wiz[1]/c-wiz[1]/div/div[@class='OoO4Vb']/div[@class='shSP']/div/div/c-wiz/div/span/div/div[@class='VgjuZe hYFhF']/div[1]/div[1]/span[1]/span[1]/div[2]/div[1]/div[1]/div[2]");

    /* dropSayfaBasiSatır elementi kapsam sayfasında verilen hata türünden biri listelendiğinde çalışıyor...*/
    By dropSayfaBasiSatir = By.xpath("//body/div[@id='yDmH0d']/c-wiz[@class='zQTmif SSPGKf eejsDc']/c-wiz[1]/div[1]/div[@class='OoO4Vb']/span[1]/div[1]/div[2]/span[1]/div[@class='shSP']/div[1]/div[1]/c-wiz[2]/div[1]/span[1]/div[1]/div[@class='VgjuZe hYFhF']/div[1]/div[1]/span[1]/span[1]/div[2]/div[1]/div[1]/div[2]");

    /* açılan dropdown menüden satir sayısı seçilecek...*/
    By selectSatirSayisi = By.xpath("//body/div[@id='yDmH0d']/c-wiz[@class='zQTmif SSPGKf eejsDc']/c-wiz[1]/div[1]/div[@class='OoO4Vb']/span[1]/div[1]/div[2]/span[1]/div[@class='shSP']/div[1]/div[1]/c-wiz[2]/div[1]/span[1]/div[1]/div[@class='VgjuZe hYFhF']/div[1]/div[1]/span[1]/span[1]/div[2]/div[2]/div[7]");

    /* console'da tür seçildiğinde açılan ekrandaki url listesindeki her satır aşağıdaki xpath ile çekilir... */
    By urlSatir = By.xpath("//body/div[@id='yDmH0d']/c-wiz[@class='zQTmif SSPGKf eejsDc']/c-wiz[1]/div[1]/div[@class='OoO4Vb']/span[1]/div[1]/div[2]/span[1]/div[@class='shSP']/div/div/c-wiz[2]/div/span/div/div[@jsname='FOLXuf']/div[2]/table/tbody/tr");

    /* Constructor */
    public ConsolePage (WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getDropBrand(){
        return driver.findElement(dropBrand);
    }

    public List<WebElement> getDropMenuBrand(){
        return driver.findElements(dropBrandMenu);
    }

    public WebElement getDropMenuBrand(String value) {return driver.findElement(By.xpath("//header/div[@role='navigation']/div[@role='menu']/div[1]/c-wiz[1]/div[2]/div[@class='cK8COe']/c-wiz[1]/div[1]/div/div[2]/div[@class=' tWfTvb']/div/div[1]/div[(@title='"+value+"')]")); }

    public WebElement getBtnKapsam(){
        return driver.findElement(btnKapsam);
    }

    public List<WebElement> getKapsamListe(){
        return driver.findElements(kapsamListe);
    }

    public List<WebElement> getMainSayfaBasiSatir(){
        return driver.findElements(dropMainSayfaBasiSatir);
    }

    public WebElement getDropSayfaBasiSatır(){
        return driver.findElement(dropSayfaBasiSatir);
    }

    public WebElement getSelectSatirSayisi(){
        return driver.findElement(selectSatirSayisi);
    }

    public List<WebElement> getUrlSatir(){
        return driver.findElements(urlSatir);
    }

    public WebElement getButonListeAdedi(String value){ return  driver.findElement(By.xpath("//div[@class='gqI7uf WR1Me']/c-wiz[@jsrenderer='te3aK']/div/span/div/div[@class='VgjuZe hYFhF']/div/div/span[1]/span/div[@role='listbox']/div[@class='OA0qNb ncFHed']/div[@data-value='"+value+"']"));}





}
