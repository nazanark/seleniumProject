package project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import sun.font.Script;
import sun.jvm.hotspot.runtime.Threads;


import java.util.Set;

public class Task1_2 {

    WebDriver driver;
    Actions actions;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);



    }

    @AfterClass
    public void cleanUp() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }

    @Test
    @Parameters({"username", "password"})
    public void test(String myUsername, String myPassword) throws InterruptedException {
        driver.get("http://icehrm-open.gamonoid.com/login.php?");
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(myUsername);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(myPassword);
        WebElement loginButton = driver.findElement(By.xpath("//button[.='Log in ']"));
        loginButton.click();
        driver.manage().window().maximize();

        WebElement switchButton = driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-new-window']"));
        switchButton.click();
        Thread.sleep(2000);


        WebElement actualText = driver.findElement(By.xpath("//div/child::p[.='Select The Employee to Switch Into']"));
        Assert.assertEquals(actualText.getText(), "Select The Employee to Switch Into");

        WebElement selectOptions = driver.findElement(By.xpath("//select[@id='switch_emp']"));
        Select select = new Select(selectOptions);
        select.selectByVisibleText("Lala Lamees");

        WebElement pressSwitch = driver.findElement(By.xpath("//button[.='Switch']"));
        pressSwitch.click();

        Thread.sleep(1000);
        WebElement lalaName = driver.findElement(By.xpath("//a[.='Lala Lamees']"));
        lalaName.getText();
        Assert.assertTrue(lalaName.isDisplayed());
        System.out.println("hello");
        System.out.println("bye");

        Thread.sleep(1000);
        WebElement changeToText = driver.findElement(By.xpath("//i[@class='fa fa-circle text-warning']/parent::a"));
        Assert.assertEquals(changeToText.getText(), "Changed To");

        WebElement brownColor = driver.findElement(By.xpath("//i[@class='fa fa-circle text-warning']"));
        String color = brownColor.getCssValue("color");
        String expectedRgba = "rgba(138, 109, 59, 1)";
        Assert.assertEquals(color, expectedRgba);

        //TASK 2

        Thread.sleep(500);

        lalaName.click();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("Personal_Information"));

        //This will scroll the web page to the bottom
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        WebElement jobDetails = driver.findElement(By.xpath("//div[.='Job Details']"));
        Assert.assertEquals(jobDetails.getText(), "Job Details");
        Thread.sleep(2000);

//        WebElement jobTitle=driver.findElement(By.xpath("//td[.='Pre-Sales Executive']"));
//        Assert.assertEquals(jobTitle.getText(),"Pre-Sales Executive");
        WebElement employeeStatus = driver.findElement(By.xpath("//td[.='Full Time Contract']"));
        Assert.assertEquals(employeeStatus.getText(), "Full Time Contract");

        WebElement supervisorName = driver.findElement(By.xpath("//td[.='IceHrm Employee']"));
        Assert.assertEquals(supervisorName.getText(), "IceHrm Employee");
        WebElement department = driver.findElement(By.xpath("//td[.='Head Office']"));
        Assert.assertEquals(department.getText(), "Head Office");

    }
}
