//package tests;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class APITest {
//
//    @Test
//    public void testGetPost() {
//
//        Response response = RestAssured
//                .given()
//                .baseUri("https://jsonplaceholder.typicode.com")
//                .when()
//                .get("/posts/1");
//
//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertTrue(response.getBody().asString().contains("userId"));
//    }
//}

package tests;

import com.hybrid.config.TestConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContextManager;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(classes = com.hybrid.HybridFrameworkApplication.class)
public class APITest {

    @Autowired
    private TestConfiguration testConfiguration;

    private TestContextManager testContextManager;

    @BeforeClass(alwaysRun = true)
    public void prepareSpringContext() throws Exception {

        testContextManager = new TestContextManager(APITest.class);

        testContextManager.prepareTestInstance(this);
    }

    @Test
    public void testGetPost() {

        System.out.println(
                "API Base URL: " + testConfiguration.getApiBaseUrl()
        );

        Response response = RestAssured
                .given()
                .baseUri(testConfiguration.getApiBaseUrl())
                .when()
                .get("/posts/1");

        Assert.assertEquals(response.getStatusCode(), 200);

        Assert.assertTrue(
                response.getBody().asString().contains("userId")
        );
    }
}
