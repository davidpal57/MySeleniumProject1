package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SelectByDropdownTest {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/dropdown";
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
    public void selectDropDown(){
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByValue("1");
        WebElement option1 = driver.findElement(By.xpath("//option[@value=\"1\"]"));
        Assert.assertTrue(option1.isSelected());
        dropdown.selectByValue("2");
        WebElement option2 = driver.findElement(By.xpath("//option[3]"));
        Assert.assertTrue(option2.isSelected());
    }
    @AfterTest(alwaysRun = true)
    public void tearDown(){
        //Inchide pagina
        System.out.println("Inchide pagina");
        driver.close();
    } //a
}
