package com.microserviceteam.microservice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigServerController {

    @Autowired
    ConfigValues configValues;

    @GetMapping(value = "/token")
    public String getToke(){
        return configValues.getToken();
    }
}
