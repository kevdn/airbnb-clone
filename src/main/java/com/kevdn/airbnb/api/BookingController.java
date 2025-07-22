package com.kevdn.airbnb.api;

import com.kevdn.airbnb.app.dto.response.ResponseDto;
import com.kevdn.airbnb.app.service.ResponseFactory;
import com.kevdn.airbnb.app.util.RequestUtil;
import com.kevdn.airbnb.domain.booking.dto.request.BookingRequest;
import com.kevdn.airbnb.domain.booking.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Slf4j
public class BookingController {

    private final BookingService service;
    private final ResponseFactory responseFactory;


    @PostMapping
    ResponseDto bookHomestay(@Valid @RequestBody BookingRequest request,
                             HttpServletRequest httpServletRequest) {
        var ipAddress = RequestUtil.getIpAddress(httpServletRequest);
        request.setIpAddress(ipAddress);

        log.info("Booking Request: {}", request);
        var response = service.book(request);
        return responseFactory.success(response);
    }

    @GetMapping("/{bookingId}/status")
    ResponseDto getBookingStatus(@PathVariable Long bookingId) {
        var response = service.getBookingStatus(bookingId);
        return responseFactory.success(response);
    }
}
