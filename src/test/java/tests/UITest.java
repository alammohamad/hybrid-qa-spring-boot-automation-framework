
package tests;

import base.BaseTest;
import utils.CSVUtils;
import pages.LoginPage;
import com.hybrid.config.TestConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.*;

import org.springframework.test.context.TestContextManager;

@SpringBootTest(classes = com.hybrid.HybridFrameworkApplication.class)
public class UITest extends BaseTest {

    @Autowired
    private TestConfiguration testConfiguration;

    private TestContextManager testContextManager;

    @BeforeClass(alwaysRun = true)
    public void prepareSpringContext() throws Exception {

        testContextManager = new TestContextManager(UITest.class);

        testContextManager.prepareTestInstance(this);
    }

//    @BeforeMethod
//    @Parameters("browser")
//    public void startBrowser(
//            @Optional("chrome") String browser) {
//
//        System.out.println("Browser received from TestNG: " + browser);
//
//        setUp(browser);
//    }
//    @BeforeMethod
//    @Parameters("browser")
//    public void startBrowser(String browser) {
//
//        System.out.println("========== START BROWSER ==========");
//        System.out.println("Browser received from TestNG: " + browser);
//
//        setUp(browser);
//
//        System.out.println("Driver after setUp(): " + getDriver());
//        System.out.println("===================================");
//    }
    
    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void startBrowser(String browser) {

        System.out.println("Browser received from TestNG: " + browser);

        setUp(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        tearDown();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws Exception {

        return CSVUtils.getCSVData("src/test/resources/data.csv");
    }

    @Test(
    	    dataProvider = "loginData",
    	    groups = {"regression", "smoke"},
    	    description = "Login with valid credentials"
    	)
    	public void testLogin(
    	        String userId,
    	        String password,
    	        String expectedResult) {

        LoginPage loginPage =
                new LoginPage(getDriver(), testConfiguration);

        loginPage.openWebsite();

        loginPage.verifyTitle();

        loginPage.login(userId, password);

        if (expectedResult.equalsIgnoreCase("success")) {

            Assert.assertTrue(
                    loginPage.isLoginSuccessful(),
                    "Expected login to succeed, but login was not successful."
            );

        } else if (expectedResult.equalsIgnoreCase("failure")) {

            Assert.assertTrue(
                    loginPage.isLoginFailed(),
                    "Expected login to fail, but login appears to have succeeded."
            );
        }
    }
}
////package tests;
////
////import base.BaseTest;
////import utils.CSVUtils;
////import org.testng.annotations.*;
////import org.testng.Assert;
////import org.openqa.selenium.By;
////
////
////public class UITest extends BaseTest {
////
////    @DataProvider(name = "loginData")
////    public Object[][] loginData() throws Exception {
////        return CSVUtils.getCSVData("src/test/resources/data.csv");
////    }
////
////    @Test(dataProvider = "loginData")
////    public void testLogin(String userId, String password) {
////
////        driver.get("https://www.facebook.com/");
////        Assert.assertTrue(driver.getTitle().contains("Facebook"));
////
////        driver.findElement(By.id("email")).sendKeys(userId);
////        driver.findElement(By.id("pass")).sendKeys(password);
////    }
////   
////}
//
////package tests;
////
////import base.BaseTest;
////import utils.CSVUtils;
////import org.testng.annotations.*;
////import org.testng.Assert;
////import org.openqa.selenium.By;
////import io.qameta.allure.Step;
////
////public class UITest extends BaseTest {
////
////    @DataProvider(name = "loginData")
////    public Object[][] loginData() throws Exception {
////        return CSVUtils.getCSVData("src/test/resources/data.csv");
////    }
////
////    @Step("Open Facebook website")
////    public void openFacebook() {
////        driver.get("https://www.facebook.com/");
////    }
////
////    @Step("Verify page title contains Facebook")
////    public void verifyTitle() {
////        Assert.assertTrue(driver.getTitle().contains("Facebook"));
////    }
////
////    @Step("Enter email: {0}")
////    public void enterEmail(String userId) {
////        driver.findElement(By.id("email")).sendKeys(userId);
////    }
////
////    @Step("Enter password")
////    public void enterPassword(String password) {
////        driver.findElement(By.id("pass")).sendKeys(password);
////    }
////
////    @Test(dataProvider = "loginData")
////    public void testLogin(String userId, String password) {
////
////        openFacebook();
////        verifyTitle();
////        enterEmail(userId);
////        enterPassword(password);
////    }
////}
//
////package tests;
////
////import base.BaseTest;
////import utils.CSVUtils;
////import pages.LoginPage;
////import org.testng.annotations.*;
////
////public class UITest extends BaseTest {
////
////    @DataProvider(name = "loginData")
////    public Object[][] loginData() throws Exception {
////        return CSVUtils.getCSVData("src/test/resources/data.csv");
////    }
////
////    @Test(dataProvider = "loginData")
////    public void testLogin(String userId, String password) {
////
////        LoginPage loginPage = new LoginPage(driver);
////
////        loginPage.openWebsite();
////        loginPage.verifyTitle();
////        loginPage.login(userId, password);
////    }
////}
//
////package tests;
////
////import base.BaseTest;
////import utils.CSVUtils;
////import pages.LoginPage;
////
////import org.testng.Assert;
////import org.testng.annotations.*;
////
////public class UITest extends BaseTest {
////
////    @BeforeMethod
////    public void startBrowser() {
////        setUp("chrome");
////    }
////
////    @AfterMethod
////    public void closeBrowser() {
////        tearDown();
////    }
////
////    @DataProvider(name = "loginData")
////    public Object[][] loginData() throws Exception {
////        return CSVUtils.getCSVData("src/test/resources/data.csv");
////    }
////
////    @Test(dataProvider = "loginData")
////    public void testLogin(
////            String userId,
////            String password,
////            String expectedResult) {
////
////        LoginPage loginPage = new LoginPage(getDriver());
////
////        loginPage.openWebsite();
////        loginPage.verifyTitle();
////        loginPage.login(userId, password);
////
////        if (expectedResult.equalsIgnoreCase("success")) {
////
////            Assert.assertTrue(
////                    loginPage.isLoginSuccessful(),
////                    "Expected login to succeed, but login was not successful."
////            );
////
////        } else if (expectedResult.equalsIgnoreCase("failure")) {
////
////            Assert.assertTrue(
////                    loginPage.isLoginFailed(),
////                    "Expected login to fail, but login appears to have succeeded."
////            );
////        }
////    }
////}
//
//
//package tests;
//
//import base.BaseTest;
//import utils.CSVUtils;
//import pages.LoginPage;
////import com.hybrid.service.TestConfigService;
//import com.hybrid.config.TestConfiguration;
//import io.qameta.allure.Allure;
//
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.test.context.ContextConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import org.testng.Assert;
//import org.testng.annotations.*;
//
////import com.hybrid.service.TestConfigService;
//import org.springframework.test.context.TestContextManager;
//
//@SpringBootTest(classes = com.hybrid.HybridFrameworkApplication.class)
//public class UITest extends BaseTest {
//	
//
//    @Autowired
//    private TestConfiguration testConfiguration;
//    private TestContextManager testContextManager;
//    
//    @BeforeClass(alwaysRun = true)
//    public void prepareSpringContext() throws Exception {
//
//        testContextManager = new TestContextManager(UITest.class);
//
//        testContextManager.prepareTestInstance(this);
//    }
//
////    @BeforeMethod
////    public void startBrowser() {
////
////        String browser = testConfigService.getBrowser();
////
////        System.out.println("Browser received from Spring: " + browser);
////
////        setUp(browser);
////    }
//    
//    @BeforeMethod
//    @Parameters("browser")
//    public void startBrowser(String browser) {
//    	     
//        System.out.println("Browser received from TestNG: " + browser);
//        Allure.parameter("Browser", browser);
//
//        setUp(browser);
//    }
//    
//
//    @AfterMethod
//    public void closeBrowser() {
//        tearDown();
//    }
//
//    @DataProvider(name = "loginData")
//    public Object[][] loginData() throws Exception {
//        return CSVUtils.getCSVData("src/test/resources/data.csv");
//    }
//
//    @Test(dataProvider = "loginData")
//    public void testLogin(
//            String userId,
//            String password,
//            String expectedResult) {
//
//        //LoginPage loginPage = new LoginPage(getDriver());
//    	//LoginPage loginPage = new LoginPage(getDriver(), testConfigService);
//    	LoginPage loginPage = new LoginPage(getDriver(), testConfiguration);
//
//        loginPage.openWebsite();
//        loginPage.verifyTitle();
//        loginPage.login(userId, password);
//
//        if (expectedResult.equalsIgnoreCase("success")) {
//
//            Assert.assertTrue(
//                    loginPage.isLoginSuccessful(),
//                    "Expected login to succeed, but login was not successful."
//            );
//
//        } else if (expectedResult.equalsIgnoreCase("failure")) {
//
//            Assert.assertTrue(
//                    loginPage.isLoginFailed(),
//                    "Expected login to fail, but login appears to have succeeded."
//            );
//        }
//    }
//}



//package tests;
//
//import base.BaseTest;
//import utils.CSVUtils;
//import pages.LoginPage;
//import com.hybrid.config.TestConfiguration;
////import io.qameta.allure.Allure;
////import io.qameta.allure.model.Parameter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.testng.Assert;
//import org.testng.annotations.*;
//import org.testng.ITestResult;
////import org.testng.annotations.Listeners;
//
//import org.springframework.test.context.TestContextManager;
//
//@SpringBootTest(classes = com.hybrid.HybridFrameworkApplication.class)
////@Listeners(listeners.AllureTestListener.class)
//public class UITest extends BaseTest {
//
//    @Autowired
//    private TestConfiguration testConfiguration;
//
//    private TestContextManager testContextManager;
//
//
//    @BeforeClass(alwaysRun = true)
//    public void prepareSpringContext() throws Exception {
//
//        testContextManager = new TestContextManager(UITest.class);
//
//        testContextManager.prepareTestInstance(this);
//    }
//
//
//    @BeforeMethod
//    @Parameters("browser")
//    public void startBrowser(String browser) {
//
//        System.out.println("Browser received from TestNG: " + browser);
//
//        // Add browser to Allure report
////        Allure.parameter("Browser", browser);
//
//        setUp(browser);
//    }
//
//
////    @AfterMethod
////    public void closeBrowser() {
////
////        tearDown();
////    }
//
////    @AfterMethod(alwaysRun = true)
////    public void closeBrowser(ITestResult result) {
////
////        if (result.getStatus() == ITestResult.FAILURE) {
////
////            System.out.println(
////                    "Test failed. Capturing screenshot for Allure: "
////                            + result.getName()
////            );
////
////            if (getDriver() != null) {
////
////                Allure.getLifecycle().addAttachment(
////                        "Failure Screenshot",
////                        "image/png",
////                        "png",
////                        ((org.openqa.selenium.TakesScreenshot) getDriver())
////                                .getScreenshotAs(
////                                        org.openqa.selenium.OutputType.BYTES
////                                )
////                );
////            }
////        }
////
////        tearDown();
////    }
//    
//    @AfterMethod(alwaysRun = true)
//    public void closeBrowser() {
//        tearDown();
//    }
//    
//    @DataProvider(name = "loginData")
//    public Object[][] loginData() throws Exception {
//
//        return CSVUtils.getCSVData("src/test/resources/data.csv");
//    }
//
//
//    @Test(dataProvider = "loginData")
//    public void testLogin(
//            String userId,
//            String password,
//            String expectedResult) {
//
//        // Safe test information for Allure
////        Allure.parameter("User ID", userId);
////        Allure.parameter("Expected Result", expectedResult);
////        Allure.parameter("Password", password, Parameter.Mode.MASKED);
//
//        // Do NOT add the password to Allure.
//        // The password is used by the test but is not reported.
//
//        LoginPage loginPage =
//                new LoginPage(getDriver(), testConfiguration);
//
//        loginPage.openWebsite();
//
//        loginPage.verifyTitle();
//
//        loginPage.login(userId, password);
//
//
////        if (expectedResult.equalsIgnoreCase("success")) {
//
////            Assert.assertTrue(
////                    loginPage.isLoginSuccessful(),
////                    "Expected login to succeed, but login was not successful."
////            );
// // temp adding below portion to make it fail and get the screen shot       	
////        	Assert.assertTrue(
////        	        false,
////        	        "TESTING ALLURE FAILURE SCREENSHOT"
////        	);
//
////        } else if (expectedResult.equalsIgnoreCase("failure")) {
////
////            Assert.assertTrue(
////                    loginPage.isLoginFailed(),
////                    "Expected login to fail, but login appears to have succeeded."
////            );
////        }
//        
//        if (expectedResult.equalsIgnoreCase("success")) {
//
//            Assert.assertTrue(
//                    loginPage.isLoginSuccessful(),
//                    "Expected login to succeed, but login was not successful."
//            );
//
//        } else if (expectedResult.equalsIgnoreCase("failure")) {
//
//            Assert.assertTrue(
//                    loginPage.isLoginFailed(),
//                    "Expected login to fail, but login appears to have succeeded."
//            );
//        }
//    }
//}