package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {


    public WebDriver driver;

    By btnSimdiBasla =By.xpath("//div[@class='ThQnOe uaHa6c JcdbFb']/div[@class='PXzS6e']/div[@class='eZynkc']/div[@class='KXgdRe qOvw5c']/div[2]/div");
    By editBoxEmail = By.xpath("//input[@type='email']");
    By btnEmailNext = By.xpath("//div[@class='dG5hZc']/div[@class='qhFLie']/div");
    By editBoxPassword = By.xpath("//input[@name='password']");
    By btnPasswordNext = By.xpath("//div[@class='dG5hZc']/div[@class='qhFLie']/div");

    /* Constructor */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getBtnSimdiBasla(){
        return driver.findElement(btnSimdiBasla);
    }

    public WebElement getEditBoxEmail(){
        return driver.findElement(editBoxEmail);
    }

    public WebElement getBtnEmailNext(){
        return driver.findElement(btnEmailNext);
    }

    public WebElement getEditBoxPassword(){
        return driver.findElement(editBoxPassword);
    }

    public WebElement getBtnPasswordNext(){
        return driver.findElement(btnPasswordNext);
    }



}


