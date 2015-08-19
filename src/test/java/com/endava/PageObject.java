package com.endava;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by msaros on 8/18/2015.
 */
public class PageObject {

    LoginPage page;
    HomePage homepage;
    WebDriver driver;






    @Before
    public void before()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        page = PageFactory.initElements(driver,LoginPage.class);
        homepage=PageFactory.initElements(driver,HomePage.class);
    }

    @Test
    public void test()
    {
        page.login("miky", "green panda");
        homepage.verifyTitle("miky");
       // homepage.submitArticle("TEST", "Acesta...");
        //homepage.changeTemplate("#25498a","#122247");
       // homepage.verifyUserMenuElements("Site Administrator");
       // homepage.editArticol("Ceva");
        //homepage.modifyArticol("Ceva","Cevaaa","http://www.sub25.ro");
        //homepage.changeSiteSetting("Guest", "15");
        //homepage.pathVerifications("");
       // homepage.verifyGreetingMessageText("Mihaela Saros");
       // homepage.search("test","18 August");
        //homepage.logout();
        homepage.loginAdministratorSite("miky","greenpanda","English");







    }





    @After
    public void after()
    {
        driver.close();

    }

}
