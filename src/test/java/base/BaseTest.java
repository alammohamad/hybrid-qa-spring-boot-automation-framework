package base;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.hybrid.driver.WebDriverFactory;

public class BaseTest {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Autowired
    private WebDriverFactory webDriverFactory;

    public void setUp(String browser) {

        System.out.println(
                "Thread: " + Thread.currentThread().getId()
                + " | Browser configured: " + browser
        );

        WebDriver webDriver = webDriverFactory.createDriver(browser);

        webDriver.manage().window().maximize();

        driver.set(webDriver);
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void tearDown() {

        WebDriver webDriver = driver.get();

        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}
//package base;
//
//import org.openqa.selenium.WebDriver;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.hybrid.driver.WebDriverFactory;
//
//public class BaseTest {
//
//    private WebDriver driver;
//
//    @Autowired
//    private WebDriverFactory webDriverFactory;
//
//    public void setUp(String browser) {
//
//        System.out.println("Browser configured: " + browser);
//
//        driver = webDriverFactory.createDriver(browser);
//
//        driver.manage().window().maximize();
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    public void tearDown() {
//
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}

//package base;
//
//import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.chrome.ChromeDriver;
////import org.openqa.selenium.firefox.FirefoxDriver;
////import org.openqa.selenium.edge.EdgeDriver;
////import io.github.bonigarcia.wdm.WebDriverManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.hybrid.driver.WebDriverFactory;
//
//public class BaseTest {
//
//    private WebDriver driver;
//    @Autowired
//    private WebDriverFactory webDriverFactory;
//    
//    public void setUp(String browser) {
//
//        System.out.println("Browser configured: " + browser);
//
//        driver = webDriverFactory.createDriver(browser);
//
//        driver.manage().window().maximize();
//    }
//
////    public void setUp(String browser) {
////
////        System.out.println("Browser configured: " + browser);
////
////        switch (browser.toLowerCase()) {
////
////        case "chrome":
////            WebDriverManager.chromedriver().setup();
////            driver = new ChromeDriver();
////            break;
////
////        case "firefox":
////            WebDriverManager.firefoxdriver().setup();
////            driver = new FirefoxDriver();
////            break;
////
//////        case "edge":
//////            WebDriverManager.edgedriver().setup();
//////            driver = new EdgeDriver();
//////            break;
////            
////        case "edge":
////            System.setProperty(
////                "webdriver.edge.driver",
////                "C:\\browserdriver\\Drivers_asOf-6-18-2025\\Edge\\msedgedriver.exe"
////            );
////            driver = new EdgeDriver();
////            break;
////
////        default:
////            throw new IllegalArgumentException(
////                    "Unsupported browser: " + browser);
////        }
////
////        driver.manage().window().maximize();
////    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    public void tearDown() {
//
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}	



//----------------------
// Was below before---
//package base;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestResult;
//import org.testng.annotations.*;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import io.qameta.allure.Attachment;
//
//public class BaseTest {
//
//    protected WebDriver driver;
//
//    @Parameters("browser")
//    @BeforeMethod
//    public void setUp(String browser) {
//
//        if (browser.equalsIgnoreCase("chrome")) {
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//        } else if (browser.equalsIgnoreCase("firefox")) {
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//        } else if (browser.equalsIgnoreCase("edge")) {
//        	// as of 8-13-2026- Edge Version 151.0.4129.78 (Official build) (64-bit)
//        	// C:\Users\moham>"C:\browserdriver\Drivers_asOf-6-18-2025\Edge\edgedriver_win64\msedgedriver.exe" --version
//        //	Microsoft Edge WebDriver 137.0.3296.83 (24e00e9cccf26ea31b8a586e427ec805341bc8ec)
//        	// downloaded: https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/?utm_source=chatgpt.com&form=MA13LH&cs=1820347785
//            System.setProperty(
//                    "webdriver.edge.driver",
//                    "C:\\browserdriver\\Drivers_asOf-6-18-2025\\Edge\\msedgedriver.exe"  // use "//" This is a Java string rule, not specifically a Selenium rule.
//                );
//                driver = new EdgeDriver();
//        }
//    }
//
//    @Attachment(value = "Screenshot", type = "image/png")
//    public byte[] takeScreenshot() {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }
//// Because Allure expects binary data (byte array) to attach images to the report.
////byte = smallest data unit (8 bits) // byte[] = array of raw binary data
////Images are binary files, not text.
////So:
////Screenshot = PNG file
////PNG file = binary data // Binary data = byte[]
//
//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            takeScreenshot();
//        }
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}


// Open cmdline from C:\Users\moham\Hybrid-RestAssuredForAPI-SeleniumForSelenium-Testng_v2 and execute below

//mvn clean test "-Dsurefire.suiteXmlFiles=testng.xml" "-Dbrowser=chrome"   // chrome only and also API calls
//mvn clean test "-DsuiteXmlFile=testng.xml" "-Dbrowser=firefox"  mvn clean test "-DsuiteXmlFile=testng.xml"
//mvn clean test "-DsuiteXmlFile=testng.xml" // all browser (works for chrome and firefox, edges has error, will fix later)
// and and also API calls
//Details about this project---
//In Real Projects (Your Scenario)/ A project has api and ui side also. When API call with rest assured but ui test with Selenium and test execution with Test NG
//UI + API Hybrid Validation
//No. RestAssured is used for API testing only. For UI automation we use Selenium. In hybrid automation frameworks, Selenium handles UI layer and RestAssured validates backend APIs.
//
//Open eclipse 2025
//eclipse workSpace: C:\Users\moham\eclipse-workspace\MohamadEclipse2025Workspace
//On eclipse shows HybridFramework  / R click on testing.xml--run as testng
//Project-----location: C:\Users\moham\Eclipse-2025\HybridFramework(Hybrid-RestAssuredForAPI-SeleniumForSelenium-Testng) // I got this zip file
//R. click on testing.pom--run as testing
//
//or go to root dir eg C:\Users\moham\eclipse-workspace\Hybrid-RestAssuredForAPI-SeleniumForSelenium-Testng_v2 cmd and execute mvn clean test or On eclipse shows HybridFramework  / R click on testing.xml--run as testng
//
//Allure report is one liner, need s to convert regular json(may use - https://jsonformatter.org/ and format json file)
//Allure report shows:
// "stop": 1771111108942  // These are Unix timestamps in milliseconds (epoch time).Meaning:Time since Jan 1, 1970, Stored in milliseconds, Used for precise duration calculation
//
//Allure stores execution time in epoch milliseconds for precision and easy duration calculation. The HTML report layer converts it into human-readable format.
//
//We use protected so that:
//Child test classes can directly access driver, but outside classes cannot.
//What Does protected Mean in Java?
//
//protected allows access:
//✅ Inside the same class
//✅ Inside the same package
//✅ In subclasses (even in different packages)
//❌ NOT accessible from unrelated external classes
//
//Why protected Is Perfect for Framework Design
//
//In automation frameworks:
//BaseTest initializes driver in @BeforeMethod
//Child test classes extend BaseTest
//They automatically inherit driver
//They use driver directly
//Example:
//public class UITest extends BaseTest {
//
//    @Test
//    public void testLogin() {
//        driver.get("https://facebook.com");
//    }
//}
//
//Here driver is accessible because it's protected.
//
//OOP Concept Behind This
//This is a combination of:
//Inheritance
//Encapsulation
//Controlled accessibility
//
//You're allowing controlled sharing of a resource.
//Because test classes extend BaseTest and need access to the WebDriver instance. Protected allows subclasses to use it while preventing unrestricted public access.
//
//Why Not private? eg. private WebDriver driver;
//Then child test classes like:
//public class UITest extends BaseTest
//would NOT be able to access driver.
//That would cause compile error.
//
//Why Not public?
//If you wrote:
//public WebDriver driver;
//Then:
//Any class anywhere can modify driver
//Break encapsulation
//Bad framework design
//Unsafe
//@Parameters("browser") from testing and comes from testing.xml// It is used to pass values from testng.xml into your test setup.
//And in testng.xml:
//<parameter name="browser" value="chrome"/>
//Purpose
//
//Allows:
//Cross-browser testing
//Runtime configuration
//Flexible execution without changing code
//
//@BeforeMethod also comes from testing// Run this method before every @Test method.
//Execution order:
//@BeforeMethod
//@Test
//@AfterMethod
//If you have 3 test methods → setup runs 3 times.
//
//@Attachment // This comes from: Allure Framework
//Purpose
//
//Tells Allure:
//Whatever this method returns → attach it to report.
//It has NOTHING to do with TestNG execution lifecycle.
//It is purely for reporting.
//
//How They Work Together------------
//TestNG starts
//   ↓
//@BeforeMethod → Browser launched
//   ↓
//@Test runs
//   ↓
//If failure → Listener calls takeScreenshot()
//   ↓
//@Attachment attaches screenshot to Allure
//
//So:
//@Parameters → Controls configuration
//@BeforeMethod → Controls test lifecycle
//@Attachment → Controls reporting
//
//TestNG controls execution lifecycle using annotations like @BeforeMethod and @Parameters. Allure integrates through annotations like @Step and @Attachment to enhance reporting without affecting test execution.
//@Step comes from: Allure framework/ Treat this method as a reporting step and show it in the report.
//If remove @step--The action will still execute ✅ But it will NOT appear as a step in Allure report ❌
//
//| Annotation      | From   | Purpose                      |
//| --------------- | ------ | ---------------------------- |
//| `@BeforeMethod` | TestNG | Controls execution lifecycle |
//| `@Parameters`   | TestNG | Pass runtime config          |
//| `@Step`         | Allure | Reporting step               |
//| `@Attachment`   | Allure | Attach file/data             |
// @DataProvide come from testng and import org.testng.annotations.DataProvider;
// Does Eclipse/Maven create testng.xml automatically?
//No, TestNG does not create it automatically.
//When you create a TestNG project in Eclipse, you can optionally let Eclipse create a default testng.xml, but usually you have to create it manually if you want custom suites, parameters, or parallel execution.
//Maven does not generate it either. It only runs tests defined in src/test/java unless you configure <suiteXmlFiles> in pom.xml.
//
// Why Do We Create testng.xml?
//testng.xml allows you to:
//Group tests into <suite> and <test>
//Define parameters (<parameter name="browser" value="chrome"/>)
//Run multiple classes in one suite
//Set parallel execution
//Control test order
//Maven will run exactly the tests defined in testng.xml
//
//If testng.xml does not exist → Maven runs all tests by default (classes with @Test)
//Always keep testng.xml in project root (<project>/testng.xml)
//If you want multi-browser execution, create multiple <test> entries with different <parameter> values
//For parallel execution, you can add: <suite name="HybridSuite" parallel="tests" thread-count="3">
//Summary----
//testng.xml is manual for custom suites, parameters, or parallel runs
//Maven + Eclipse can run tests without it, but you lose control over suite structure
//For hybrid Selenium + API + Allure projects, it’s recommended to create one manually
