package com.spring.application.configuration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
@Slf4j
public class HttpAgent<T> {

    @HystrixCommand(fallbackMethod = "fallback")
    public T getApi(String clientId, String clientSecret, RestTemplate restTemplate, String url, Long id, Class<T> cls, int errCount) {


        //todo: put in config
        int maxRetryCount = 5;
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("client_id", clientId);
        headers.set("client_secret", clientSecret);
        // build the request
        HttpEntity request = new HttpEntity(headers);
        // make an HTTP GET request with headers
        try {
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    cls,
                    id
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                log.info("Request Successful in get Api");
                return response.getBody();
            } else {
                if(errCount <= maxRetryCount) {
                    getApi(clientId, clientSecret, restTemplate, url, id, cls,errCount+1);
                }
                log.error("Request Failed in get Api  url:{} and status code:{}",  url, response.getStatusCode());
            }
        } catch (Exception ex) {
            log.error("Error in get Api having url:{} and stack trace:{}", url, ex.getMessage());
        }
        return null;
    }

    private String fallback(){
         log.error("GET REST Api is not working!!!");
         return null;
    }
}
