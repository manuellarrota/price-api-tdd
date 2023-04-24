package com.between.pricesapi.repository;

import com.between.pricesapi.model.Price;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PriceRepository {
    Price findPriceByDateAndBrandAndProduct(
            @Param("applicationDate") Date applicationDate,
            @Param("productId") Long productId,
            @Param("brandId") Integer brandId);
}
