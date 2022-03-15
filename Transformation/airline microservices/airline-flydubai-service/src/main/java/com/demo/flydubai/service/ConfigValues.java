package com.demo.flydubai.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("s3")
@RefreshScope
public class ConfigValues {

    private String token;
    private String port;
    private String accesskey;
    private String secretkey;

    public String getToken() {
        return token;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }
}
