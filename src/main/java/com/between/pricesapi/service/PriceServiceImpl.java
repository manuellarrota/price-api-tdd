package com.between.pricesapi.service;

import com.between.pricesapi.model.Price;
import com.between.pricesapi.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPriceByDateAndBrandAndProduct(Date applicationDate, Long productId, Integer brandId) {
        return priceRepository.getPriceByDateAndBrandAndProduct(applicationDate, productId, brandId);
    }
}
