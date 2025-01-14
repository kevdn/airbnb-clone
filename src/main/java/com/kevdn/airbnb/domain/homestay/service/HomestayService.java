package com.kevdn.airbnb.domain.homestay.service;

import com.kevdn.airbnb.domain.booking.constant.AvailabilityStatus;
import com.kevdn.airbnb.domain.common.constant.ResponseCode;
import com.kevdn.airbnb.domain.common.exception.BusinessException;
import com.kevdn.airbnb.domain.homestay.dto.HomestayDTO;
import com.kevdn.airbnb.domain.homestay.dto.request.HomestaySearchRequest;
import com.kevdn.airbnb.domain.homestay.entity.Homestay;
import com.kevdn.airbnb.domain.homestay.repository.HomestayRepository;
import com.kevdn.airbnb.infrastructure.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomestayService {

    private final HomestayRepository repository;


    public Homestay getHomestayById(Long id) {
        var homestay = repository.findById(id).orElse(null);
        return homestay;
    }

    public List<HomestayDTO> searchHomestays(HomestaySearchRequest request) {
        request.setStatus(AvailabilityStatus.AVAILABLE);

        var checkinDate = request.getCheckinDate();
        var checkoutDate = request.getCheckoutDate();
        final var currentDate = LocalDate.now();

        if (checkinDate.isBefore(currentDate) || checkinDate.isAfter(checkoutDate)) {
            throw new BusinessException(ResponseCode.CHECKIN_DATE_INVALID);
        }

        int nights = (int) DateUtil.getDiffInDays(checkinDate, checkoutDate);
        checkoutDate = checkoutDate.minusDays(1);

        return repository.searchHomestay(
                request.getLongitude(),
                request.getLatitude(),
                request.getRadius(),
                checkinDate,
                checkoutDate,
                nights,
                request.getGuests(),
                request.getStatus().getValue()
        );
    }
}
