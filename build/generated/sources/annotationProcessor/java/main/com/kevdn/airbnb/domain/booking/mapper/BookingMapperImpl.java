package com.kevdn.airbnb.domain.booking.mapper;

import com.kevdn.airbnb.domain.booking.dto.request.BookingDto;
import com.kevdn.airbnb.domain.booking.dto.request.BookingStatusResponse;
import com.kevdn.airbnb.domain.booking.entity.Booking;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-20T21:23:57+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingDto toBookingDto(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingDto bookingDto = new BookingDto();

        bookingDto.setBookingId( booking.getId() );
        bookingDto.setUserId( booking.getUserId() );
        bookingDto.setHomestayId( booking.getHomestayId() );
        if ( booking.getCheckinDate() != null ) {
            bookingDto.setCheckinDate( DateTimeFormatter.ISO_LOCAL_DATE.format( booking.getCheckinDate() ) );
        }
        if ( booking.getCheckoutDate() != null ) {
            bookingDto.setCheckoutDate( DateTimeFormatter.ISO_LOCAL_DATE.format( booking.getCheckoutDate() ) );
        }
        bookingDto.setGuests( booking.getGuests() );
        bookingDto.setStatus( booking.getStatus() );
        bookingDto.setSubtotal( booking.getSubtotal() );
        bookingDto.setDiscount( booking.getDiscount() );
        bookingDto.setTotalAmount( booking.getTotalAmount() );
        bookingDto.setCurrency( booking.getCurrency() );
        bookingDto.setRequestId( booking.getRequestId() );
        bookingDto.setNote( booking.getNote() );

        return bookingDto;
    }

    @Override
    public BookingStatusResponse toBookingStatusResponse(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingStatusResponse bookingStatusResponse = new BookingStatusResponse();

        bookingStatusResponse.setBookingId( booking.getId() );
        bookingStatusResponse.setUserId( booking.getUserId() );
        bookingStatusResponse.setHomestayId( booking.getHomestayId() );
        bookingStatusResponse.setStatus( booking.getStatus() );

        return bookingStatusResponse;
    }
}
