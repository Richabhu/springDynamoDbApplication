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


    public boolean validateApi(String url)
    {
        HttpAgent<String> httpAgent = new HttpAgent<>();

        String response = httpAgent.getApi(null, null,
                restTemplate, url, null, String.class, 0);
        if (ObjectUtils.isEmpty(response)) {
            log.error("Request Failed for url: {}", url);
            return false;
        }
        return true;
    }

}
