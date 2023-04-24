package com.between.pricesapi.controller;

import com.between.pricesapi.dto.PriceRequestDto;
import com.between.pricesapi.dto.PriceResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prices")
public class PriceController {
    @PostMapping
    public ResponseEntity<PriceResponseDto> getPriceByDateAndBrandAndProduct(@RequestBody PriceRequestDto priceRequestDto ) {
        return null;
    }
}
