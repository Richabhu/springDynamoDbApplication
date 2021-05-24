package com.spring.application.services.miscellaneous;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.application.configuration.HttpAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ValidateService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fall", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000") })
    public boolean validateApi(String url, int retryCount)
    {
        HttpAgent<String> httpAgent = new HttpAgent<>();

        //todo: put it in config
        int maxRetryCount = 5;

        String response = httpAgent.getApi(null, null,
                restTemplate, url, null, String.class);
        if (ObjectUtils.isEmpty(response)) {
            if (retryCount <= maxRetryCount)
                validateApi(url, retryCount + 1);
            else {
                log.error("Request Failed for url: {}", url);
                return false;
            }
        }
        return true;
    }


    private String fall(){
        return "Validate API is Out of Service!!!";
    }
}
