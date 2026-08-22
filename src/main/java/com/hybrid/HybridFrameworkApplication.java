//package com.hybrid;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication   // This is the main Spring Boot application
//public class HybridFrameworkApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(HybridFrameworkApplication.class, args);
//    }
//}

package com.hybrid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hybrid.component.TestRunnerComponent;

@SpringBootApplication
public class HybridFrameworkApplication implements CommandLineRunner {

    private final TestRunnerComponent testRunnerComponent;

    public HybridFrameworkApplication(TestRunnerComponent testRunnerComponent) {
        this.testRunnerComponent = testRunnerComponent;
    }

    public static void main(String[] args) {
        SpringApplication.run(HybridFrameworkApplication.class, args);
    }

    @Override
    public void run(String... args) {
        testRunnerComponent.runTest();
    }
}