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

public class CheckboxesTest {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/checkboxes";

    // mai sus am declarat driver-ul si url-ul inainte de test ca sa fie vazute in cadrul clasei LoginTest
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
    public void checkboxes() {
        WebElement checkbox1 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
        if (checkbox1.isSelected()) {
            checkbox1.click();
        }
        Assert.assertFalse(checkbox1.isSelected());

        WebElement checkbox2 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));
        if (checkbox2.isSelected()) {
            checkbox2.click();
        }
        Assert.assertFalse(checkbox2.isSelected());
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        //Inchide pagina
        System.out.println("Inchide pagina");
        driver.close();
    }
} //a
