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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Task3 {

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

    @Test(dataProvider = "addNew")
    public void task3(String salaryComponent, String amount, String details) throws InterruptedException {
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

        WebElement pressSwitch = driver.findElement(By.xpath("//button[.='Switch']"));
        pressSwitch.click();
        Thread.sleep(2000);
        WebElement lalaName = driver.findElement(By.xpath("//a[.='Lala Lamees']"));
        lalaName.click();

        //This will scroll the web page to the bottom
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        WebElement financeButton = driver.findElement(By.xpath("//i[@class='fa fa-calculator']/following-sibling::span"));
        financeButton.click();

        WebElement salaryOption = driver.findElement(By.xpath("//a[@href='https://icehrm-open.gamonoid.com/?g=modules&n=salary&m=module_Finance']"));
        salaryOption.click();
        Thread.sleep(2000);
        WebElement addNewButton = driver.findElement(By.xpath("//button[@class='btn btn-small btn-primary']"));
        addNewButton.click();

        Thread.sleep(1000);
        WebElement salaryComponentOption = driver.findElement(By.xpath("//select[@type='select-one']"));
        salaryComponentOption.sendKeys(salaryComponent);
        Thread.sleep(2000);
        WebElement amountField = driver.findElement(By.id("amount"));
        amountField.sendKeys(amount);
        Thread.sleep(2000);
        WebElement detailsField = driver.findElement(By.id("details"));
        detailsField.sendKeys(details);

        WebElement saveButton = driver.findElement(By.xpath("//button[@class='saveBtn btn btn-primary pull-right']"));
        saveButton.click();
        Thread.sleep(2000);
        WebElement searchField = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchField.sendKeys(salaryComponent);
        Thread.sleep(5000);
        WebElement allInfoLine = driver.findElement(By.xpath("//tr[@class='odd']"));
        Assert.assertTrue(allInfoLine.getText().contains(salaryComponent)
                && allInfoLine.getText().contains(amount)
                && allInfoLine.getText().contains(details));
        Thread.sleep(3000);
        WebElement expectedText=driver.findElement(By.xpath("//div[@id='grid_info']"));
        Assert.assertTrue(expectedText.getText().contains("Showing 1 to 1 of 1 entries"));
    }

    @DataProvider(name = "addNew")
    public Object[][] getTestData() {
        {
            return new Object[][]{{"Fixed Allowance", "200000", "Test1"},
                    {"Car Allowance", "1000000", "Test2"},
                    {"Telephone Allowance", "100", "Test3"},
                    {"Regular Hourly Pay", "70", "Test4"},
                    {"Overtime Hourly Pay", "80", "Test5"}};
        }
    }
}

