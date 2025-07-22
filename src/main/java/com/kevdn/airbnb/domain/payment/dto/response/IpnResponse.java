package com.kevdn.airbnb.domain.payment.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IpnResponse {

    @JsonProperty("RspCode")
    private String responseCode;

    @JsonProperty("Message")
    private String message;
}
