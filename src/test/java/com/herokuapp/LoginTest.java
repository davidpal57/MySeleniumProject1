package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//daca ne intreaba cineva cu ce am lucrat pana acum zicem cu: Selenium, TestNG si IntelliJ
public class LoginTest {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/login";
    // mai sus am declarat driver-ul si url-ul inainte de test ca sa fie vazute in cadrul clasei LoginTest
    @Parameters({"browserP"})
    @BeforeTest // am separat de testul de login sa fie mai frumos aranjat codul
    public void setUp(String browser){
        switch (browser){
            case "chrome": driver = new ChromeDriver(); break;
            case "edge": driver = new EdgeDriver(); break;
            case "firefox": driver = new FirefoxDriver(); break;
            default: driver = new ChromeDriver();
        }
        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test(testName = "Login Test")
    @Parameters({"usernameP", "passwordP", "subHeaderP"})
    public void login(String username, String password, String subHeader){
        //1. deschide pagina Form authentication
//        System.out.println("Deschide pagina Form Authentication");
//        WebDriver driver = new ChromeDriver();
//        String url = "https://the-internet.herokuapp.com/login";
//        driver.get(url); // deschide pagina
//        driver.manage().window().maximize(); // face fereastra mare
//        System.out.println("Asteapta 3 secunde");
//        sleep(2000);

        //2. click username & enter user: tomsmith
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys(username);
        //3. click password & enter password: SuperSecretPassword!
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        //4. click login button
        System.out.println("Click login button");
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click(); //a
        //Expected results: "Welcome to the Secure Area" is displayed
        System.out.println("Verificam continutul subheaderului");
//        WebElement secureAreaSubeheader = driver.findElement(By.className("subheader"));
//        Assert.assertTrue(secureAreaSubeheader.isDisplayed());
//        Assert.assertEquals(subHeader, secureAreaSubeheader.getText());

//        String secureUrl = "https://the-internet.herokuapp.com/secure";
//        Assert.assertEquals(driver.getCurrentUrl(), secureUrl);
//
        WebElement successMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(successMessage.getText().contains(subHeader)); // verificam daca mesajul de mai sus contine succesMessageContent (valoarea lui)
    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        //Inchide pagina
        System.out.println("Inchide pagina");
        driver.close();
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
