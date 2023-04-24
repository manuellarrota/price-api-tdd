package com.between.pricesapi.service;

import com.between.pricesapi.model.Price;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class PriceServiceImpl implements PriceService {
    @Override
    public Price findPriceByDateAndBrandAndProduct(Date applicationDate, Long productId, Integer brandId) {
        return null;
    }
}
