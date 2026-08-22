//package com.hybrid.driver;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//
//import org.springframework.stereotype.Component;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//@Component
//public class WebDriverFactory {
//
//    public WebDriver createDriver(String browser) {
//
//        System.out.println("Spring WebDriverFactory - Browser: " + browser);
//
//        switch (browser.toLowerCase()) {
//
//        case "chrome":
//
//            WebDriverManager.chromedriver().setup();
//            return new ChromeDriver();
//
//        case "firefox":
//
//            WebDriverManager.firefoxdriver().setup();
//            return new FirefoxDriver();
//
//        case "edge":
//
//            System.setProperty(
//                "webdriver.edge.driver",
//                "C:\\browserdriver\\Drivers_asOf-6-18-2025\\Edge\\msedgedriver.exe"
//            );
//
//            return new EdgeDriver();
//
////        case "edge":
////
////            WebDriverManager.edgedriver().setup();
////
////            return new EdgeDriver();
//            
//        default:
//
//            throw new IllegalArgumentException(
//                "Unsupported browser: " + browser
//            );
//        }
//    }
//}

//package com.hybrid.driver;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//
//import org.springframework.stereotype.Component;
//
//import com.hybrid.config.TestConfiguration;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//@Component
//public class WebDriverFactory {
//
//    private final TestConfiguration testConfiguration;
//
//    public WebDriverFactory(TestConfiguration testConfiguration) {
//        this.testConfiguration = testConfiguration;
//    }
//
//    public WebDriver createDriver(String browser) {
//
//        System.out.println(
//            "Spring WebDriverFactory - Browser: " + browser
//        );
//
//        switch (browser.toLowerCase()) {
//
//        case "chrome":
//
//            WebDriverManager.chromedriver().setup();
//
//            return new ChromeDriver();
//
//        case "firefox":
//
//            WebDriverManager.firefoxdriver().setup();
//
//            return new FirefoxDriver();
//
//        case "edge":
//
//            System.setProperty(
//                "webdriver.edge.driver",
//                testConfiguration.getEdgeDriverPath()
//            );
//
//            return new EdgeDriver();
//
//        default:
//
//            throw new IllegalArgumentException(
//                "Unsupported browser: " + browser
//            );
//        }
//    }
//}

package com.hybrid.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.stereotype.Component;

import com.hybrid.config.TestConfiguration;

import io.github.bonigarcia.wdm.WebDriverManager;

@Component
public class WebDriverFactory {

    private final TestConfiguration testConfiguration;

    public WebDriverFactory(TestConfiguration testConfiguration) {
        this.testConfiguration = testConfiguration;
    }

    public WebDriver createDriver(String browser) {

        System.out.println(
                "Spring WebDriverFactory - Browser: " + browser
        );

        switch (browser.toLowerCase()) {

        case "chrome":

            WebDriverManager.chromedriver().setup();

            return new ChromeDriver();

        case "firefox":

            WebDriverManager.firefoxdriver().setup();

            return new FirefoxDriver();

        case "edge":

            String edgeDriverPath =
                    testConfiguration.getEdgeDriverPath();

            System.out.println(
                    "Edge driver path from Spring: " + edgeDriverPath
            );

            System.setProperty(
                    "webdriver.edge.driver",
                    edgeDriverPath
            );

            return new EdgeDriver();

        default:

            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        }
    }
}