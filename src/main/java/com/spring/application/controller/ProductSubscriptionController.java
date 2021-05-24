package com.spring.application.controller;

import com.spring.application.models.ProductSubscription;
import com.spring.application.responses.ProductSubscriptionResponse;
import com.spring.application.responses.common.StatusResponse;
import com.spring.application.responses.common.enumeration.ErrorCodes;
import com.spring.application.responses.common.enumeration.SuccessCodes;
import com.spring.application.services.ProductSubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestController
@RequestMapping("/product/")
public class ProductSubscriptionController {

    @Autowired
    ProductSubscriptionService productSubscriptionService;

    protected ProductSubscriptionResponse createResponse(ProductSubscription productSubscription) {
        ProductSubscriptionResponse response = new ProductSubscriptionResponse();
        response.setProductSubscription(productSubscription);
        return response;
    }

    @RequestMapping(
            value = {"subscribe/{id}"},
            method = {RequestMethod.POST}
    )
    public ProductSubscriptionResponse create(@RequestBody ProductSubscription input, @PathVariable String id) throws Exception {

        ProductSubscriptionResponse response = this.createResponse(null);

        try {
            ProductSubscription result = productSubscriptionService.newSubscription(input, id);
            response = this.createResponse(result);
            response.setStatus(new StatusResponse(SuccessCodes.OK, 1));
            return response;
        }

        catch (EntityNotFoundException dataNotFound){
            log.error("Error occurred: {}", dataNotFound.getMessage());
            response.setStatus(new StatusResponse(ErrorCodes.NOT_FOUND, 0));
            return response;

        }

    }

    @RequestMapping(
            value = {"subscribe/{id}"},
            method = {RequestMethod.PUT}
    )
    public ProductSubscriptionResponse update(@RequestBody ProductSubscription input, @PathVariable String id) throws Exception {

        ProductSubscriptionResponse response = this.createResponse(null);

        try {
            ProductSubscription result = productSubscriptionService.updateSubscription(input, id);
            response = this.createResponse(result);
            response.setStatus(new StatusResponse(SuccessCodes.OK, 1));
            return response;
        }

        catch (EntityNotFoundException dataNotFound){
            log.error("Error occurred: {}", dataNotFound.getMessage());
            response.setStatus(new StatusResponse(ErrorCodes.NOT_FOUND, 0));
            return response;

        }

    }

}
