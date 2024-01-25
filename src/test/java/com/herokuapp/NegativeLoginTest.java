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

public class NegativeLoginTest {
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
            default: driver = new ChromeDriver(); // a
        }
        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    @Parameters({"usernameP", "passwordP", "errorP"})
    public void loginWithInvalidUser(String username, String password, String error){
        /*WebDriver driver = new FirefoxDriver();
        WebDriver driver = new EdgeDriver();
        WebDriver driver = new SafariDriver();*/
        WebElement usernameInput = driver.findElement(By.cssSelector("#username"));
        usernameInput.sendKeys(username);
        //XPATH => //nume[@atribute='value']
        //    ex1: //input[@id='username']
        //    ex2: //*[@id="password"] => cauta in orice element (* - face asta) unde gaseste id="password"
        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//button[@class='radius']"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(errorMessage.getText().contains(error));
    }
    @AfterTest(alwaysRun = true)
    public void tearDown(){
        //Inchide pagina
        System.out.println("Inchide pagina");
        driver.close();
    }
//    @Test(priority = 2, groups = {"smoke", "all"}) // smoke testing = ? > cauta pe net ce inseamna
//    public void loginWithInvalidPassword(){
//        WebDriver driver = new ChromeDriver();
//        String url = "https://the-internet.herokuapp.com/login";
//        driver.get(url);
//        driver.manage().window().maximize();
//        WebElement usernameInput = driver.findElement(By.cssSelector("#username"));
//        usernameInput.sendKeys("tomsmith");
//        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
//        passwordInput.sendKeys("wrongPassword");
//        WebElement loginButton = driver.findElement(By.xpath("//button[@class='radius']"));
//        loginButton.click();
//        WebElement errorMessage = driver.findElement(By.id("flash"));
//        String invalidPasswordMessageContent = "Your password is invalid!";
//        Assert.assertTrue(errorMessage.getText().contains(invalidPasswordMessageContent));
//        driver.close();
//    }
//    @Test(priority = 3)
//    public void NegativeLogin() {
//        WebDriver driver = new ChromeDriver();
//        String url = "https://the-internet.herokuapp.com/login";
//        driver.get(url);
//        driver.manage().window().maximize();
//        sleep(2000);
//        WebElement usernameInput = driver.findElement(By.id("username"));
//        usernameInput.sendKeys("tomsmith");
//        sleep(2000);
//        WebElement passwordInput = driver.findElement(By.id("password"));
//        passwordInput.sendKeys("SuperSecretPassword");
//        sleep(2000);
//        WebElement loginButton = driver.findElement(By.className("radius"));
//        loginButton.click();
//        sleep(2000);
//        WebElement invalidPassword = driver.findElement(By.id("flash"));
//        String flashErrorContent = "Your password is invalid!";
//        Assert.assertTrue(invalidPassword.getText().contains(flashErrorContent));
//        sleep(2000);
//        driver.close();
//    }
//    public static void sleep(int milliseconds) {
//        try {
//            Thread.sleep(milliseconds);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
