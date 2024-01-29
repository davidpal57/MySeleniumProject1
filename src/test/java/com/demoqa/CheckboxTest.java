package com.demoqa;
//tema demoqa.com a

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckboxTest {
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
        WebElement cookieConsent = driver.findElement(By.className("fc-cta-consent"));
        if (cookieConsent.isDisplayed()){
            cookieConsent.click();
        }
    }
    @Test
    @Parameters({"resultP1"})
    public void checkbox(String result1){
//        WebElement elements = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]"));
//        elements.click();
//        sleep(2000);
        WebElement menuCheckbox = driver.findElement(By.id("item-1"));
        menuCheckbox.click();
        sleep(2000);
//        WebElement checkbox1 = driver.findElement(By.className("rct-checkbox"));
//        WebElement checkbox1 = driver.findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/span/label/span[1]"));
//        checkbox1.click();
//        sleep(2000);
        WebElement expandAll = driver.findElement(By.className("rct-option-expand-all"));
        expandAll.click();

//        By loadingImage = By.id("loading image ID");
//
//        WebDriverWait wait = new WebDriverWait(driver, 2000);
//
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingImage));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        WebElement checkbox4 = driver.findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/ol/li[2]/ol/li[2]/ol/li[2]/span/label/span[1]"));
        checkbox4.click();
        WebElement resultMessage = driver.findElement(By.id("result"));
        Assert.assertTrue(resultMessage.getText().contains(result1));



    }
    @AfterTest(alwaysRun = true)
    public void teardown(){
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
