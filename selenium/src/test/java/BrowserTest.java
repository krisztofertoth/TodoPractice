import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class BrowserTest {



        static void screenshot(String screenshotname, WebDriver driver) throws IOException {
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(screenshotname));
        }



    public static void main(String[] args) throws IOException{

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver = new Augmenter().augment(driver);
        String path = System.getProperty("user.dir");
        String projectPath = path+"\\screenshots";

        try {
            driver.get("http://127.0.0.1:5500/index.html");
            WebElement todoInput = driver.findElement(new By.ByXPath("//input[@class='new-todo']"));
            screenshot(projectPath + "\\emptylist.JPG", driver);
            todoInput.sendKeys("Test Todo");
            todoInput.sendKeys(Keys.RETURN);
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//input[@class='new-todo']"))).sendKeys("Test Todo2");
            screenshot(projectPath + "\\create.JPG", driver);
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//input[@class='new-todo']"))).sendKeys(Keys.RETURN);
            screenshot(projectPath + "\\created.JPG", driver);
            WebElement todo1Completed = driver.findElement(new By.ByXPath("//li[1]/div[@class='view' and 1]/input[@class='toggle' and 1]"));
            todo1Completed.click();
            screenshot(projectPath + "\\completed.JPG", driver);
            WebElement todo1UnCompleted = driver.findElement(new By.ByXPath("//li[@class='todo completed']/div[@class='view' and 1]/input[@class='toggle' and 1]"));
            todo1UnCompleted.click();
            try {
                new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//section/label[1]"))).click();
            }catch (org.openqa.selenium.StaleElementReferenceException ex){
                WebElement allCompleted = driver.findElement(new By.ByXPath("//section/label[1]"));
                allCompleted.click();
            }
            screenshot(projectPath+"\\allcompleted.JPG",driver);
            try {
                new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//section/label[1]"))).click();
            }catch (org.openqa.selenium.StaleElementReferenceException ex){
                new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//section/label[1]"))).click();
            }
            WebElement todo2 = driver.findElement(new By.ByXPath("//li[2]/div[@class='view' and 1]/label[1]"));
            try {
                new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//li[1]/div[@class='view' and 1]/label[1]"))).click();
            }catch (org.openqa.selenium.StaleElementReferenceException ex){
                WebElement todo = driver.findElement(new By.ByXPath("//li[1]/div[@class='view' and 1]/label[1]"));
                todo.click();
            }
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//li[1]/div[@class='view' and 1]/label[1]"))).click();
            screenshot(projectPath+"\\delete.JPG",driver);
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//li[1]/div[@class='view' and 1]/button[@class='destroy' and 1]"))).click();
            screenshot(projectPath + "\\deleted.JPG", driver);
            Actions act = new Actions(driver);
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//li[1]/div[@class='view' and 1]/label[1]"))).click();
            try {
                act.moveToElement(todo2).doubleClick().build().perform();
            }catch (org.openqa.selenium.StaleElementReferenceException ex){
                new WebDriverWait(driver, 5);
                WebElement todo2_1 = driver.findElement(new By.ByXPath("//li[1]/div[@class='view' and 1]/label[1]"));
                act.moveToElement(todo2_1).doubleClick().build().perform();
            }
            screenshot(projectPath + "\\edit.JPG", driver);
        }finally {
            driver.close();
            driver.quit();
        }
    }
}
