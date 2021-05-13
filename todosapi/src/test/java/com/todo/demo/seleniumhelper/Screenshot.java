package com.todo.demo.seleniumhelper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    private String screenshotname;

    private WebDriver driver;

    public Screenshot(String screenshotname, WebDriver driver) {
        this.screenshotname = screenshotname;
        this.driver = driver;
    }

    public String getScreenshotname() {
        return screenshotname;
    }

    public void setScreenshotname(String screenshotname) {
        this.screenshotname = screenshotname;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void takescreenshot(String screenshotname) throws IOException {
        File srcFile = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(this.screenshotname+screenshotname));
    }
}
