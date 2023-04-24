package com.between.pricesapi.service;

import com.between.pricesapi.model.Price;

import java.util.Date;

public interface PriceService {

    Price findPriceByDateAndBrandAndProduct(
            Date applicationDate,
            Long productId,
            Integer brandId);
}
