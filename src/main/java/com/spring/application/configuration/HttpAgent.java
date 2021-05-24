package com.spring.application.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
@Slf4j
public class HttpAgent<T> {

    public T getApi(String clientId, String clientSecret, RestTemplate restTemplate, String url, Long id, Class<T> cls) {


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
                log.error("Request Failed in get Api  url:{} and status code:{}",  url, response.getStatusCode());
            }
        } catch (Exception ex) {
            log.error("Error in get Api having url:{} and stack trace:{}", url, ex.getMessage());
        }
        return null;
    }

}
