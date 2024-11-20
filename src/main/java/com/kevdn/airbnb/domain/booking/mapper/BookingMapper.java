package com.kevdn.airbnb.domain.booking.mapper;

import com.kevdn.airbnb.domain.booking.dto.request.BookingDto;
import com.kevdn.airbnb.domain.booking.dto.request.BookingStatusResponse;
import com.kevdn.airbnb.domain.booking.dto.response.BookingResponse;
import com.kevdn.airbnb.domain.booking.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(source = "id", target = "bookingId")
    BookingDto toBookingDto(Booking booking);

    @Mapping(source = "id", target = "bookingId")
    BookingStatusResponse toBookingStatusResponse(Booking booking);
}