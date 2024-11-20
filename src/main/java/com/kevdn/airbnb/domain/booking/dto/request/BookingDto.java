package com.kevdn.airbnb.domain.booking.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookingDto {
    private Long bookingId;
    private Long userId;
    private Long homestayId;
    private String checkinDate;
    private String checkoutDate;
    private Integer guests;

    private Integer status;

    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal totalAmount;
    private String currency;

    private String requestId;
    private String note;
}