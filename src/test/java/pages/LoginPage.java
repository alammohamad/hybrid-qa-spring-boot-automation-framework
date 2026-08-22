package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
//import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.hybrid.config.TestConfiguration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private TestConfiguration testConfiguration;

    // Constructor
//    public LoginPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }

    public LoginPage(WebDriver driver, TestConfiguration testConfiguration) {
        this.driver = driver;
        this.testConfiguration = testConfiguration;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Locators
    private By usernameField =
            By.xpath("//input[@name='username']");

    private By passwordField =
            By.xpath("//input[@name='password']");

    private By loginButton =
            By.xpath("//button[@type='submit']");

    // Successful login indicator
    private By dashboard =
            // was By.xpath("//h6[text()='Dashboard']");
            By.xpath("//span[text()='Dashboard']");

    // Invalid login error message
    private By invalidLoginMessage =
            By.xpath("//p[contains(@class,'oxd-alert-content-text')]");
  //p[@class='oxd-text oxd-text--p oxd-alert-content-text']
  //p[contains(@class, 'oxd-alert-content-text') and text()='Invalid credentials']

    // Open OrangeHRM
    //@Step("Open OrangeHRM website")
    public void openWebsite() {
        //driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    	//driver.get(testConfigService.getBaseUrl());
    	driver.get(testConfiguration.getBaseUrl());
    }
    

    // Verify page title
    //@Step("Verify OrangeHRM login page")
    public void verifyTitle() {
        Assert.assertTrue(
                driver.getTitle().contains("OrangeHRM"),
                "Title does not contain OrangeHRM"
        );
    }

    // Enter username
    //@Step("Enter username: {0}")
    public void enterUsername(String username) {
    	WebElement usernameElement = wait.until(
    	        ExpectedConditions.visibilityOfElementLocated(usernameField)
    	);
    	usernameElement.sendKeys(username);
    }

    // Enter password
    //@Step("Enter password")
    public void enterPassword(String password) {
    	WebElement passwordElement = wait.until(
    	        ExpectedConditions.visibilityOfElementLocated(passwordField)
    	);
    	passwordElement.sendKeys(password);
    }

    // Click Login
    //@Step("Click Login button")
    public void clickLogin() {
    	WebElement loginElement = wait.until(
    	        ExpectedConditions.elementToBeClickable(loginButton)
    	);
    	loginElement.click();
    }

    // Perform login
    //@Step("Login with username and password")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Verify successful login
//    @Step("Verify successful login")
//    public boolean isLoginSuccessful() {
//        try {
//            return driver.findElement(dashboard).isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }
    
    
    //@Step("Verify successful login")
    public boolean isLoginSuccessful() {

        try {
            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(dashboard)
            ).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
    
   
    // Verify failed login
    //@Step("Verify login failure")
    public boolean isLoginFailed() {
        try {
            WebElement errorMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(invalidLoginMessage)
            );
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}


    

//    // Locators
//    private By emailField = By.id("email");
//    private By passwordField = By.id("pass");
//    private By loginButton = By.name("login");
//
//    // Actions
//
//    @Step("Open Facebook website")
//    public void openWebsite() {
//        driver.get("https://www.facebook.com/");
//    }
//
//    @Step("Verify page title contains Facebook")
//    public void verifyTitle() {
//        Assert.assertTrue(driver.getTitle().contains("Facebook"),
//                "Title does not contain Facebook");
//    }
//
//    @Step("Enter email: {0}")
//    public void enterEmail(String email) {
//        driver.findElement(emailField).sendKeys(email);
//    }
//
//    @Step("Enter password")
//    public void enterPassword(String password) {
//        driver.findElement(passwordField).sendKeys(password);
//    }
//
//    @Step("Click login button")
//    public void clickLogin() {
//        driver.findElement(loginButton).click();
//    }
//
//    @Step("Perform login with email and password")
//    public void login(String email, String password) {
//        enterEmail(email);
//        enterPassword(password);
//        clickLogin();
//    }
//}




