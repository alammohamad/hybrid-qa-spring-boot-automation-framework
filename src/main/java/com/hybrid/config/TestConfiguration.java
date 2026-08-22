//package com.hybrid.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//@Component
//@ConfigurationProperties(prefix = "app")
//public class TestConfiguration {
//
//    private String baseUrl;
//    private String environment;
//    
//    public String getEnvironment() {
//        return environment;
//    }
//
//    public void setEnvironment(String environment) {
//        this.environment = environment;
//    }
//
//    public String getBaseUrl() {
//        return baseUrl;
//    }
//
//    public void setBaseUrl(String baseUrl) {
//        this.baseUrl = baseUrl;
//    }
//}

//package com.hybrid.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//@Component
//@ConfigurationProperties(prefix = "app")
//public class TestConfiguration {
//
//    private String baseUrl;
//    private String environment;
//    private String edgeDriverPath;
//
//    public String getEnvironment() {
//        return environment;
//    }
//
//    public void setEnvironment(String environment) {
//        this.environment = environment;
//    }
//
//    public String getBaseUrl() {
//        return baseUrl;
//    }
//
//    public void setBaseUrl(String baseUrl) {
//        this.baseUrl = baseUrl;
//    }
//
//    public String getEdgeDriverPath() {
//        return edgeDriverPath;
//    }
//
//    public void setEdgeDriverPath(String edgeDriverPath) {
//        this.edgeDriverPath = edgeDriverPath;
//    }
//}

package com.hybrid.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class TestConfiguration {

    private String baseUrl;
    private String environment;
    private String edgeDriverPath;
    private String apiBaseUrl;

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    public void setApiBaseUrl(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }
    
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getEdgeDriverPath() {
        return edgeDriverPath;
    }

    public void setEdgeDriverPath(String edgeDriverPath) {
        this.edgeDriverPath = edgeDriverPath;
    }
}
