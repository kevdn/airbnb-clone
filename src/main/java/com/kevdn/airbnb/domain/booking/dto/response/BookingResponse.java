package com.kevdn.airbnb.domain.booking.dto.response;


import com.kevdn.airbnb.domain.booking.dto.request.BookingDto;
import com.kevdn.airbnb.domain.payment.dto.response.InitPaymentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    BookingDto booking;

    InitPaymentResponse payment;
}
