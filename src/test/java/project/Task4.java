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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Task4 {

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
    public void task4() throws InterruptedException {
        driver.get("http://icehrm-open.gamonoid.com/login.php?");
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("admin");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("admin");
        WebElement loginButton = driver.findElement(By.xpath("//button[.='Log in ']"));
        loginButton.click();
        driver.manage().window().maximize();

        WebElement switchButton = driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-new-window']"));
        switchButton.click();
        Thread.sleep(2000);

        WebElement selectOptions = driver.findElement(By.xpath("//select[@id='switch_emp']"));
        Select select = new Select(selectOptions);
        select.selectByVisibleText("Lala Lamees");
        Thread.sleep(2000);

        WebElement pressSwitch = driver.findElement(By.xpath("//button[.='Switch']"));
        pressSwitch.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(2000);

        WebElement payrollButton=driver.findElement(By.xpath("//i[@class='fa fa-file-archive']"));
        payrollButton.click();

        WebElement salaryButton=driver.findElement(By.xpath("//a[@href='https://icehrm-open.gamonoid.com/?g=admin&n=salary&m=admin_Payroll']"));
        salaryButton.click();

        WebElement employeeSalaryComponent=driver.findElement(By.id("tabEmployeeSalary"));
        employeeSalaryComponent.click();
        Thread.sleep(2000);
        WebElement filterButton=driver.findElement(By.xpath("//button[.='Filter ']"));
        filterButton.click();


        Thread.sleep(2000);
        WebElement dropdown=driver.findElement(By.xpath("//select[@type='select-one']"));
        Select select1=new Select(dropdown);
        select1.selectByVisibleText("Lala Lamees");

        WebElement clickFilter=driver.findElement(By.xpath("//button[@class='filterBtn btn btn-primary pull-right']"));
        clickFilter.click();

        WebElement expectedText=driver.findElement(By.id("EmployeeSalary_resetFilters"));
        Assert.assertTrue(expectedText.getText().contains("Lala Lamees"));

        Thread.sleep(2000);
        WebElement payrollButton2=driver.findElement(By.xpath("//i[@class='fa fa-file-archive']"));
        payrollButton2.click();

        WebElement salaryButton2=driver.findElement(By.xpath("//a[@href='https://icehrm-open.gamonoid.com/?g=admin&n=salary&m=admin_Payroll']"));
        salaryButton2.click();

        WebElement employeeSalaryComponent2=driver.findElement(By.id("tabEmployeeSalary"));
        employeeSalaryComponent2.click();
        Thread.sleep(2000);

    }
}
