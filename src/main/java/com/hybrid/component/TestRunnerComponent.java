package com.hybrid.component;

import org.springframework.stereotype.Component;
import com.hybrid.config.TestConfiguration;

@Component
public class TestRunnerComponent {

    private final TestConfiguration testConfiguration;

    public TestRunnerComponent(TestConfiguration testConfiguration) {
        this.testConfiguration = testConfiguration;
    }

    public void runTest() {

        String environment = testConfiguration.getEnvironment();
        String baseUrl = testConfiguration.getBaseUrl();

        System.out.println(
                "Running test in environment: " + environment
        );

        System.out.println(
                "Application URL: " + baseUrl
        );
    }
}

//package com.hybrid.component;
//
//import org.springframework.stereotype.Component;
//
//import com.hybrid.service.TestConfigService;
//
//@Component
//public class TestRunnerComponent {
//
//    private final TestConfigService testConfigService;
//
//    public TestRunnerComponent(TestConfigService testConfigService) {
//        this.testConfigService = testConfigService;
//    }
//
//    public void runTest() {
//
//        String environment = testConfigService.getEnvironment();
//
//        System.out.println(
//                "Running QA test in environment: " + environment
//        );
//    }
//}


//package com.hybrid.component;
//
//import org.springframework.stereotype.Component;
//
//import com.hybrid.service.TestConfigService;
//
//@Component
//public class TestRunnerComponent {
//
//    private final TestConfigService testConfigService;
//
//    public TestRunnerComponent(TestConfigService testConfigService) {
//        this.testConfigService = testConfigService;
//    }
//
//    public void runTest() {
//
//        String environment = testConfigService.getEnvironment();
//        String browser = testConfigService.getBrowser();
//
//        System.out.println("Running QA test in environment: " + environment);
//        System.out.println("Running QA test with browser: " + browser);
//    }
//}
//--------
// was below before
//package com.hybrid.component;
//
//import org.springframework.stereotype.Component;
//
//import com.hybrid.service.TestConfigService;
//
//@Component
//public class TestRunnerComponent {
//
//    private final TestConfigService testConfigService;
//
//    public TestRunnerComponent(TestConfigService testConfigService) {
//        this.testConfigService = testConfigService;
//    }
//
//    public void runTest() {
//        String environment = testConfigService.getEnvironment();
//        String browser = testConfigService.getBrowser();
//
//        System.out.println("Running QA test in environment: " + environment);
//        System.out.println("Running QA test with browser: " + browser);
//    }
//}