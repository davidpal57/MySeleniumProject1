package com.demoqa;

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

//tema demoqa.com a
public class RadioButtonTest {
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
    @Parameters({"resultP2"})
    public void radioButton(String result){
        WebElement menuRadioButton = driver.findElement(By.id("item-2"));
        menuRadioButton.click();
        sleep(2000);
        WebElement yesRadioButton = driver.findElement(By.xpath("//label[@for='yesRadio']"));
        yesRadioButton.click();
        WebElement impressiveRadioButton = driver.findElement(By.xpath("//label[@for='impressiveRadio']"));
        impressiveRadioButton.click();
        System.out.println(result);
        WebElement textSuccess = driver.findElement(By.className("mt-3"));
        Assert.assertTrue(textSuccess.getText().contains(result));
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
