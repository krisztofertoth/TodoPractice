package com.todo.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class SeleniumScreesnshots {

    static void screenshot(String screenshotname, WebDriver driver) throws IOException {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(screenshotname));
    }

    public static void main(String[] args) throws IOException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String projectPath = System.getProperty("user.dir");
        String screenshotsLocation = projectPath+"\\screenshots";
        try {
            driver.get("http://localhost:8081");
            WebElement userName = driver.findElement(new By.ByXPath("//div[1]/input[@class='form-control' and 1]"));
            WebElement password = driver.findElement(new By.ByXPath("//div[2]/input[@class='form-control' and 1]"));
            WebElement login = driver.findElement(new By.ByXPath("//button"));
            screenshot(screenshotsLocation+"\\loginview.jpg",driver);
            userName.sendKeys("Test");
            password.sendKeys("test123");
            login.click();
            screenshot(screenshotsLocation+"\\todosview.jpg",driver);
            WebElement todoCreate = driver.findElement(new By.ByXPath("//div[@class='form-group']/input[1]"));
            todoCreate.sendKeys("SeleniumTest");
            screenshot(screenshotsLocation + "\\create.jpg", driver);
            WebElement add = driver.findElement(new By.ByXPath("//div[@class='form-group']/button[1]"));
            add.click();
            screenshot(screenshotsLocation + "\\created.jpg", driver);
            WebElement done = driver.findElement(new By.ByXPath("//button[@class='btn-primary' and 1 and text()='Done' and @id='1']"));
            done.click();
            screenshot(screenshotsLocation+"\\completed.jpg",driver);
            WebElement logout = driver.findElement(new By.ByXPath("//a"));
            logout.click();
            WebElement register = driver.findElement(new By.ByXPath("//a[@class='nav-link']"));
            register.click();
            screenshot(screenshotsLocation+"\\registerview.jpg",driver);
        }finally {
            driver.close();
            driver.quit();
        }
    }
}
