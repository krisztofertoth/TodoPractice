package com.todo.demo;

import com.todo.demo.seleniumhelper.Screenshot;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class SeleniumScreesnshots {



    public static void main(String[] args) throws IOException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String projectPath = System.getProperty("user.dir");
        String screenshotsLocation = projectPath+"\\screenshots";
        Screenshot screenshot = new Screenshot(screenshotsLocation,driver);
        try {
            driver.get("http://localhost:8081");
            WebElement userName = driver.findElement(new By.ByXPath("//div[1]/input[@class='form-control' and 1]"));
            WebElement password = driver.findElement(new By.ByXPath("//div[2]/input[@class='form-control' and 1]"));
            WebElement login = driver.findElement(new By.ByXPath("//button"));
            screenshot.takescreenshot("\\loginview.jpg");
            userName.sendKeys("Test");
            password.sendKeys("test123");
            login.click();
            screenshot.takescreenshot("\\todosview.jpg");
            WebElement todoCreate = driver.findElement(new By.ByXPath("//div[@class='form-group']/input[1]"));
            todoCreate.sendKeys("SeleniumTest");
            screenshot.takescreenshot("\\create.jpg");
            WebElement add = driver.findElement(new By.ByXPath("//div[@class='form-group']/button[1]"));
            add.click();
            screenshot.takescreenshot("\\created.jpg");
            WebElement done = driver.findElement(new By.ByXPath("//button[@class='btn-primary' and 1 and text()='Done' and @id='1']"));
            done.click();
            screenshot.takescreenshot("\\completed.jpg");
            WebElement logout = driver.findElement(new By.ByXPath("//a"));
            logout.click();
            WebElement register = driver.findElement(new By.ByXPath("//a[@class='nav-link']"));
            register.click();
            screenshot.takescreenshot("\\registerview.jpg");
        }finally {
            driver.close();
            driver.quit();
        }
    }
}
