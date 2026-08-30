//package utils;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import base.BaseTest;
//
//public class ExtentTestListener implements ITestListener {
//
//    private static ExtentReports extent =
//            ExtentManager.getInstance();
//
//    private static ThreadLocal<ExtentTest> extentTest =
//            new ThreadLocal<>();
//
//
//    @Override
//    public void onStart(ITestContext context) {
//
//        System.out.println(
//                "Extent Report Started: "
//                        + context.getName()
//        );
//    }
//
//
//    @Override
//    public void onTestStart(ITestResult result) {
//
//        String testName =
//                result.getMethod().getMethodName();
//
//        ExtentTest test =
//                extent.createTest(testName);
//
//        extentTest.set(test);
//
//        test.info("Test started");
//
//        test.info(
//                "Environment: "
//                        + System.getProperty(
//                                "env",
//                                "QA"
//                        ).toUpperCase()
//        );
//
//        Object[] parameters =
//                result.getParameters();
//
//        if (parameters != null) {
//
//            for (Object parameter : parameters) {
//
//                if (parameter != null) {
//
//                    String value =
//                            parameter.toString();
//
//                    /*
//                     * Do not report passwords.
//                     */
//                    if (value.equalsIgnoreCase("admin123")) {
//                        value = "********";
//                    }
//
//                    test.info(
//                            "Test Parameter: "
//                                    + value
//                    );
//                }
//            }
//        }
//    }
//
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//
//        extentTest.get().pass(
//                "Test Passed"
//        );
//    }
//
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//
//        ExtentTest test =
//                extentTest.get();
//
//        test.fail(
//                "Test Failed: "
//                        + result.getThrowable()
//        );
//
//        try {
//
//            Object instance =
//                    result.getInstance();
//
//            if (instance instanceof BaseTest) {
//
//                WebDriver driver =
//                        ((BaseTest) instance)
//                                .getDriver();
//
//                if (driver != null) {
//
//                    String screenshot =
//                            ((TakesScreenshot) driver)
//                                    .getScreenshotAs(
//                                            OutputType.BASE64
//                                    );
//
//                    test.fail(
//                            "Failure Screenshot",
//                            MediaEntityBuilder
//                                    .createScreenCaptureFromBase64String(
//                                            screenshot
//                                    )
//                                    .build()
//                    );
//                }
//            }
//
//        } catch (Exception e) {
//
//            test.warning(
//                    "Unable to capture screenshot: "
//                            + e.getMessage()
//            );
//        }
//    }
//
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//
//        extentTest.get().skip(
//                "Test Skipped"
//        );
//    }
//
//
//    @Override
//    public void onFinish(ITestContext context) {
//
//        ExtentManager.flush();
//
//        System.out.println(
//                "Extent Report Generated"
//        );
//    }
//}



package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentTestListener implements ITestListener {

    private static ExtentReports extent =
            ExtentManager.getInstance();

    private static ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();


    @Override
    public void onStart(ITestContext context) {

        System.out.println(
                "Extent Report Started: "
                        + context.getName()
        );
    }


    @Override
    public void onTestStart(ITestResult result) {

//    	String testName =
//    	        result.getMethod().getDescription();
//    	if (testName == null || testName.trim().isEmpty()) {
//    	    testName = result.getMethod().getMethodName();
//    	}
//
//    	System.out.println("===== REPORT TEST NAME =====");
//    	System.out.println("Method Name: " + result.getMethod().getMethodName());
//    	System.out.println("Description: " + result.getMethod().getDescription());
//    	System.out.println("Final Test Name: " + testName);
//    	System.out.println("============================");
    	
    	String testName = getTestName(result);
    	
        ExtentTest test =
                extent.createTest(testName);

        extentTest.set(test);

        test.info("Test started");

        String browser = getBrowser(result);
        Object[] testNgParameters = result.getTestContext()
                .getCurrentXmlTest() != null
                ? new Object[]{result.getTestContext().getCurrentXmlTest().getParameter("browser")}
                : null;

        if (testNgParameters != null && testNgParameters[0] != null) {
            browser = testNgParameters[0].toString();
        }

        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length >= 3) {

            String expectedResult = parameters[2].toString();

            if ("success".equalsIgnoreCase(expectedResult)) {
                testName = "Login with valid credentials";
            } else if ("failure".equalsIgnoreCase(expectedResult)) {
                testName = "Login with invalid credentials";
            }
        }
        
        String userId = parameters != null && parameters.length > 0
                && parameters[0] != null ? parameters[0].toString() : "";

        String environment = System.getProperty("env", "QA").toUpperCase();
        ExtentManager.recordStart(testName, browser, environment, userId);

        test.info(
                "Environment: "
                        + System.getProperty(
                                "env",
                                "QA"
                        ).toUpperCase()
        );

//        test.info("Browser: " + browser.toUpperCase());
        test.info("Execution Type: " + browser.toUpperCase());

        if (parameters != null) {

            for (Object parameter : parameters) {

                if (parameter != null) {

                    String value =
                            parameter.toString();

                    /*
                     * Do not report passwords.
                     */
                    if (value.equalsIgnoreCase("admin123")) {
                        value = "********";
                    }

                    test.info(
                            "Test Parameter: "
                                    + value
                    );
                }
            }
        }
    }


    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().pass(
                "Test Passed"
        );

        String browser = getBrowser(result);
//        ExtentManager.recordEnd(
//                result.getMethod().getMethodName(), browser, "PASS");
        ExtentManager.recordEnd(
                getTestName(result), browser, "PASS");
        
    }


    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test =
                extentTest.get();

        test.fail(
                "Test Failed: "
                        + result.getThrowable()
        );

//        ExtentManager.recordEnd(
//                result.getMethod().getMethodName(), getBrowser(result), "FAIL");
        ExtentManager.recordEnd(
                getTestName(result), getBrowser(result), "FAIL");

        try {

            Object instance =
                    result.getInstance();

            if (instance instanceof BaseTest) {

                WebDriver driver =
                        ((BaseTest) instance)
                                .getDriver();

                if (driver != null) {

                    String screenshot =
                            ((TakesScreenshot) driver)
                                    .getScreenshotAs(
                                            OutputType.BASE64
                                    );

                    test.fail(
                            "Failure Screenshot",
                            MediaEntityBuilder
                                    .createScreenCaptureFromBase64String(
                                            screenshot
                                    )
                                    .build()
                    );
                }
            }

        } catch (Exception e) {

            test.warning(
                    "Unable to capture screenshot: "
                            + e.getMessage()
            );
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.get().skip(
                "Test Skipped"
        );

//        ExtentManager.recordEnd(
//                result.getMethod().getMethodName(), getBrowser(result), "SKIPPED"); 

        ExtentManager.recordEnd(
                getTestName(result), getBrowser(result), "SKIPPED");
    }
    
//    private String getBrowser(ITestResult result) {
//        String browser = result.getTestContext()
//                .getCurrentXmlTest()
//                .getParameter("browser");
//        return browser == null ? "unknown" : browser;
//    }

//    private String getTestName(ITestResult result) {
//
//        String description = result.getMethod().getDescription();
//
//        if (description != null && !description.trim().isEmpty()) {
//            return description;
//        }
//
//        return result.getMethod().getMethodName();
//    }
    
    private String getTestName(ITestResult result) {

        Object[] parameters = result.getParameters();

        if (parameters != null && parameters.length >= 3) {

            String expectedResult = parameters[2].toString();

            if ("success".equalsIgnoreCase(expectedResult)) {
                return "Login with valid credentials";
            }

            if ("failure".equalsIgnoreCase(expectedResult)) {
                return "Login with invalid credentials";
            }
        }

        String description = result.getMethod().getDescription();

        if (description != null && !description.trim().isEmpty()) {
            return description;
        }

        return result.getMethod().getMethodName();
    }
    
    
    private String getBrowser(ITestResult result) {

        if (result.getTestContext().getCurrentXmlTest() != null) {

            String browser = result.getTestContext()
                    .getCurrentXmlTest()
                    .getParameter("browser");

            if (browser != null && !browser.trim().isEmpty()) {
                return browser;
            }
        }

        return "API";
    }
    
    @Override
    public void onFinish(ITestContext context) {

        ExtentManager.flush();

        System.out.println(
                "Extent Report Generated"
        );
    }
}