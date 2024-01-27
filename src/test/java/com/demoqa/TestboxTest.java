package com.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

//tema demoqa.com a
public class TestboxTest {
    WebDriver driver;
    String url = "https://demoqa.com/elements";

    @Parameters({"browserP"})
    @BeforeTest // am separat de testul de login sa fie mai frumos aranjat codul
    public void setUp(String browser) {
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    @Parameters({"usernameP", "emailP", "currentAddressP", "permanentAddressP", "resultP"})
    public void radioButton(String username, String email, String currentAddress, String permanentAddress, String result) {
        WebElement menuTextBox = driver.findElement(By.id("item-0"));
        menuTextBox.click();
        sleep(2000);
        WebElement usernameTestbox = driver.findElement(By.id("userName"));
        usernameTestbox.sendKeys(username);
        WebElement emailTestbox = driver.findElement(By.id("userEmail"));
        emailTestbox.sendKeys(email);
        WebElement currentAddressTestbox = driver.findElement(By.id("currentAddress"));
        currentAddressTestbox.sendKeys(currentAddress);
        WebElement permanentAddressTestbox = driver.findElement(By.id("permanentAddress"));
        permanentAddressTestbox.sendKeys(permanentAddress);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        WebElement textSuccess = driver.findElement(By.id("output"));
        Assert.assertTrue(textSuccess.getText().contains(result));
    }

    @AfterTest(alwaysRun = true)
    public void teardown() {
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
