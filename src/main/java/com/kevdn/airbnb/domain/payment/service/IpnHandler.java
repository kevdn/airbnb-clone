package com.kevdn.airbnb.domain.payment.service;

import com.kevdn.airbnb.domain.payment.dto.response.IpnResponse;

import java.util.Map;

public interface IpnHandler {
    IpnResponse process(Map<String, String> params);
}
