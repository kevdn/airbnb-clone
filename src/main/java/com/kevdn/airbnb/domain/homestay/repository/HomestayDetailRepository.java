package com.kevdn.airbnb.domain.homestay.repository;

import com.kevdn.airbnb.domain.homestay.dto.request.HomestaySearchRequest;
import com.kevdn.airbnb.domain.homestay.dto.response.HomestayDetail;

import java.util.List;

public interface HomestayDetailRepository {


    List<HomestayDetail> searchHomestays(HomestaySearchRequest request);

}
