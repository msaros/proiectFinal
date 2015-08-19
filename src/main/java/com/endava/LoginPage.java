package com.endava;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.Timestamp;
import java.util.concurrent.TimeUnit;

/**
 * Created by msaros on 8/18/2015.
 */
public class LoginPage {

    @FindBy(id="modlgn-username")
    private WebElement userNameField;

    @FindBy(id="modlgn-passwd")
    private WebElement passwordField;


    @FindBy(name = "Submit")
    private WebElement loginButton;


    //locatori



    private WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
        driver.get("http://localhost:8081/joomla/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }


    public HomePage login(String username, String password)
    {
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.submit();

        HomePage home= PageFactory.initElements(driver,HomePage.class);
        return home;
    }






}
