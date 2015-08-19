package com.endava;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import org.apache.bcel.generic.Select;
import org.apache.xpath.XPath;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by msaros on 8/18/2015.
 */
public class HomePage {

    @FindBy(className = "site-title")
    private WebElement titleTxt;

    @FindBy(xpath = "//a[@href='/joomla/index.php/submit-an-article']")
    private WebElement submitButton;

    @FindBy(id = "jform_title")
    private WebElement articolTtitle;

    @FindBy(xpath = "//a[@title='Toggle editor']")
    private WebElement toogleButton;

    @FindBy(id = "jform_articletext")
    private WebElement articleText;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement saveArticleButton;

    @FindBy(xpath = "//a[@href='/joomla/index.php/template-settings']")
    private WebElement templateSettingsButton;

    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveTemplateSettingsButton;

    @FindBy(xpath = ".//input[@id='params_templateColor']")
    private WebElement templateColorField;

    @FindBy(xpath = ".//input[@id='params_templateBackgroundColor']")
    private WebElement templateBackgroundColorField;

    @FindBy(xpath = "//a[@class='btn dropdown-toggle']")
    private WebElement editArticleButton;

    @FindBy(xpath = ".//*[@class='hasTooltip icon-edit tip']']")
    private WebElement editDropDownEditButton;

    @FindBy(xpath = "//button[contains(.,'\n" + " Save')]")
    private WebElement saveSiteSettingsButton;

    @FindBy(xpath = ".//div[@class='login-greeting']")
    private WebElement greetingMessage;

    @FindBy(id = "mod-search-searchword")
    private WebElement searchField;

    @FindBy(xpath = ".//span[@class='badge badge-info']")
    private WebElement numberOfResults;

    @FindBy(xpath = ".//dl[@class='search-results']")
    private WebElement containerResults;

    @FindBy(xpath = ".//div[@class='logout-button']/input[@value='Log out']")
    private WebElement logoutButton;

    @FindBy(id = "mod-login-username")
    private WebElement usernameAdministratorButton;


    @FindBy(id="mod-login-password")
    private WebElement passwordAdministratorButton;

    @FindBy(xpath = ".//a[@class='chzn-single']")
    private WebElement languageButton;

    @FindBy(xpath = "//button[contains(.,'\n" +
            "\t\t\t\t\t\t Log in\t\t\t\t\t')]")
    private WebElement loginAdministratorButton;


















    //locatori

    private WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver=driver;
    }


    public void verifyTitle(String name)
    {
        String siteTitle=titleTxt.getAttribute("title");
        assertTrue(siteTitle.contains(name));
    }

    public void submitArticle(String title, String text)
    {
        submitButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        articolTtitle.sendKeys(title);
        toogleButton.click();
        articleText.sendKeys(text);
        saveArticleButton.click();

    }


    public void changeTemplate(String colorCode, String backgroundCode )
    {

        templateSettingsButton.click();
        templateColorField.clear();
        templateColorField.sendKeys(colorCode);
        templateBackgroundColorField.clear();
        templateBackgroundColorField.sendKeys(backgroundCode);
        saveTemplateSettingsButton.click();


    }

    // functia de mai jos cauta in lista paginilor din User Menu si daca o gaseste, o acceseaza -va fi
    //folosita si in alte functii ca sa intri direct pe pagini in functie de numele paginii

    public void verifyUserMenuElements(String exemplu)
    {
        List<WebElement> menuElementList = driver.findElements(By.xpath(" .//ul[@class='nav menu']/li/a"));
        Iterator<WebElement> itr = menuElementList.iterator();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for(WebElement elem : menuElementList)
        {
            //System.out.println(elem.getText());

            if(elem.getText().contains(exemplu))
            {
               elem.click();
                break;
            }
        }

        }



    // se preia lista ultimelor articole publicate si se face cautarea in functie de nume preluat de la consola

    public void editArticol(String articol)
    {

        List<WebElement> menuElementList = driver.findElements(By.xpath(".//span[@itemprop='name']"));
        Iterator<WebElement> itr = menuElementList.iterator();
        for(WebElement elem : menuElementList)
        {
            //System.out.println(elem.getText());

            if(elem.getText().contains(articol))
            {
                elem.click();
                break;
            }
        }

        /*WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[@class='caret']")));
        WebElement ceva=driver.findElement(By.xpath(".//li[1]/a/span[@itemprop='name']"));
        ceva.click();
        Actions action = new Actions(driver);
        action.moveToElement(editArticleButton).moveToElement(driver.findElement(By.xpath(".//*[@class='hasTooltip icon-edit tip']"))).click().build().perform();
        */


        // dupa ce s-a dat click pe articol se intra pe pagina de editare

        driver.findElement(By.xpath(".//span[@class='caret']")).click();
        driver.findElement(By.xpath(".//li[@class='edit-icon']/a/span")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    //am editat un articol prin adaugarea textului la sfarsitul textului existent in articol
    //cu javascript se pot adauga noduri sau se poate insera textul oriunde in document


    public void modifyArticol(String articol, String titlu, String body)
    {
        editArticol(articol);
        articolTtitle.clear();
        articolTtitle.sendKeys(titlu);
        toogleButton.click();
       /* JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("var newParagraph = document.createElement('p');\n" +
                "    var new=document.createTextNode('body');\n" +
                "    document.getElementById(\"jform_articletext\").appendChild(newParagraph);\n");

        */


        String continut =articleText.getText();
        articleText.clear();
        continut+=body;
        articleText.sendKeys(continut);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //saveArticleButton.click();



    }





    //se poate si cu select.selectByVisibleText("nume");

    public void changeSiteSetting(String access, String limit)
    {

        verifyUserMenuElements("Site Settings");
        driver.findElement(By.xpath(".//*[@id='jform_access_chzn']/a/div/b")).click();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> profileList=driver.findElements(By.xpath(".//ul[@class='chzn-results']/li"));
        for(WebElement elem : profileList)
        {
            //System.out.println(elem.getText());

            if(elem.getText().contains(access))
            {
                elem.click();
                break;
            }
        }


        driver.findElement(By.xpath(".//*[@id='jform_list_limit_chzn']/a/span")).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        List<WebElement> limitList=driver.findElements(By.xpath(".//ul[@class='chzn-results']/li"));
        for(WebElement elem: limitList)
        {
            if(elem.getText().contains(limit))
            {
                elem.click();
                break;
            }

        }


        //saveSiteSettingsButton.click();





    }



    //aceasta verificare se poate folosi pentru orige pagina
    public void pathVerifications(String path)
    {
       List<WebElement> pathList= driver.findElements(By.xpath(".//*[@id='content']/ul/li/a[@class='pathway']"));
       String aux="";
        for (WebElement elem : pathList)
        {
            aux+=elem.getText();
        }

        assertTrue("Calea este corecta", aux.contains(path));
    }



    public void verifyGreetingMessageText(String nume)
    {
        String message=greetingMessage.getText();
        assertTrue(message.contains(nume));
    }




    public void verifyAlertMessages(String mesaj)
    {
        String alertMessage=driver.findElement(By.xpath(".//p[@class='alert-message']")).getText();
        assertTrue(driver.findElement(By.xpath(".//p[@class='alert-message']")).isDisplayed());
        assertEquals(mesaj, alertMessage);


    }


    public void search(String key ,String date)
    {

        searchField.sendKeys(key);
        searchField.submit();
        System.out.println(numberOfResults.getText());
        //Double count=Double.parseDouble(numberOfResults.getText());
        //List<WebElement> searchResultsList = driver.findElements(By.xpath(".//dt[@class='result-title']/a"));  --- pentru numele rezultatelor
        List<WebElement> dateResultsList = containerResults.findElements(By.xpath(".//dd[@class='result-created']"));
        int count=0;
        for (WebElement elem: dateResultsList)
        {
            if(elem.getText().contains(date))
            {
                count++;
            }
        }
        System.out.println(count);


    }



    public  void logout()
    {
        logoutButton.click();

    }


    public void loginAdministratorSite(String username, String password, String language)
    {
        verifyUserMenuElements("Site Administrator");
        usernameAdministratorButton.sendKeys(username);
        passwordAdministratorButton.sendKeys(password);

        languageButton.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> limitList=driver.findElements(By.xpath(".//ul[@class='chzn-results']/li"));


        for(WebElement elem: limitList)
        {
            if(elem.getText().contains(language))
            {
                elem.click();
                break;
            }

        }

        loginAdministratorButton.click();








    }



    }













