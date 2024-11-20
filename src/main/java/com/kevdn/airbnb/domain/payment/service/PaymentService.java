package com.kevdn.airbnb.domain.payment.service;

import com.kevdn.airbnb.domain.payment.dto.request.InitPaymentRequest;
import com.kevdn.airbnb.domain.payment.dto.response.InitPaymentResponse;

public interface PaymentService {

    InitPaymentResponse init(InitPaymentRequest request);
}