package com.spring.application.responses;

import com.spring.application.models.ProductSubscription;
import com.spring.application.responses.common.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductSubscriptionResponse extends BaseResponse {
    private ProductSubscription productSubscription;
}