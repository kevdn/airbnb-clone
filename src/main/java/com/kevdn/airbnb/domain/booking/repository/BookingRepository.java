package com.kevdn.airbnb.domain.booking.repository;

import com.kevdn.airbnb.domain.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepository extends JpaRepository<Booking, Long> {
}
