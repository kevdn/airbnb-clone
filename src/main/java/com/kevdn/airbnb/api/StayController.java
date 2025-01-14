package com.kevdn.airbnb.api;

import com.kevdn.airbnb.app.dto.response.ResponseDto;
import com.kevdn.airbnb.app.service.ResponseFactory;
import com.kevdn.airbnb.domain.homestay.dto.request.HomestaySearchRequest;
import com.kevdn.airbnb.domain.homestay.entity.Homestay;
import com.kevdn.airbnb.domain.homestay.service.HomestayService;
import com.kevdn.airbnb.infrastructure.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/homestays")
@RequiredArgsConstructor
public class StayController {

    private final HomestayService service;
    private final ResponseFactory responseFactory;


    @GetMapping("/{id}")
    public Homestay getHomestayById(@PathVariable Long id) {
        return service.getHomestayById(id);
    }


    @GetMapping
    public ResponseDto searchHomestay(@RequestParam(value = "longitude") Double longitude,
                                      @RequestParam(value = "latitude") Double latitude,
                                      @RequestParam(value = "radius") Double radius,
                                      @RequestParam(value = "checkin_date") String checkinDate,
                                      @RequestParam(value = "checkout_date") String checkoutDate,
                                      @RequestParam(value = "guests") Integer guests) {

        var request = HomestaySearchRequest.builder()
                .longitude(longitude)
                .latitude(latitude)
                .radius(radius)
                .checkinDate(DateUtil.parse(checkinDate))
                .checkoutDate(DateUtil.parse(checkoutDate))
                .guests(guests)
                .build();

        var result = service.searchHomestays(request);

        return responseFactory.success(result);
    }
}
