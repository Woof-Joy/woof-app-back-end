package org.woof.woofjoybackend.configuration.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Value("${java.version}")
    private String javaVersion;

    @GetMapping("/info/java")
    public String getJavaVersion() {
        return "Java version: " + javaVersion;
    }
}
