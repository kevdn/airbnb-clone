package com.kevdn.airbnb.domain.booking.mapper;

import com.kevdn.airbnb.domain.booking.dto.response.BookingResponse;
import com.kevdn.airbnb.domain.booking.entity.Booking;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-21T01:44:35+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingResponse toResponse(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingResponse bookingResponse = new BookingResponse();

        bookingResponse.setBookingId( booking.getId() );
        bookingResponse.setUserId( booking.getUserId() );
        bookingResponse.setHomestayId( booking.getHomestayId() );
        if ( booking.getCheckinDate() != null ) {
            bookingResponse.setCheckinDate( DateTimeFormatter.ISO_LOCAL_DATE.format( booking.getCheckinDate() ) );
        }
        if ( booking.getCheckoutDate() != null ) {
            bookingResponse.setCheckoutDate( DateTimeFormatter.ISO_LOCAL_DATE.format( booking.getCheckoutDate() ) );
        }
        bookingResponse.setGuests( booking.getGuests() );
        bookingResponse.setStatus( booking.getStatus() );
        bookingResponse.setSubtotal( booking.getSubtotal() );
        bookingResponse.setDiscount( booking.getDiscount() );
        bookingResponse.setTotalAmount( booking.getTotalAmount() );
        bookingResponse.setCurrency( booking.getCurrency() );
        bookingResponse.setRequestId( booking.getRequestId() );
        bookingResponse.setNote( booking.getNote() );

        return bookingResponse;
    }
}
