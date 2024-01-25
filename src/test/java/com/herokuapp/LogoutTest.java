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

public class LogoutTest {
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
    @Test
    @Parameters({"usernameP", "passwordP", "flashSuccessP"})
    public void logout(String username, String password, String flashSuccess){
//        WebDriver driver = new ChromeDriver();
//        String url = ("https://the-internet.herokuapp.com/login");
//        driver.get(url);
//        driver.manage().window().maximize();
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();
        WebElement logoutButton = driver.findElement(By.xpath("//*[@class='button secondary radius']"));
        logoutButton.click();
        WebElement logoutMessage = driver.findElement(By.xpath("//*[@class=\"flash success\"]"));
        Assert.assertTrue(logoutMessage.getText().contains(flashSuccess));
    }
    @AfterTest(alwaysRun = true)
    public void tearDown(){
        //Inchide pagina
        System.out.println("Inchide pagina");
        driver.close();
    }
}
