package com.between.pricesapi.controller;

import com.between.pricesapi.dto.PriceRequestDto;
import com.between.pricesapi.dto.PriceResponseDto;
import com.between.pricesapi.model.Price;
import com.between.pricesapi.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("prices")
@Slf4j
public class PriceController {
    private final PriceService priceService;
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }


    @PostMapping
    public ResponseEntity<PriceResponseDto> getPriceByDateAndBrandAndProduct(@RequestBody PriceRequestDto priceRequestDto ) {
        Date applicationDate;
        try {
            applicationDate =  df.parse(priceRequestDto.getApplicationDate());
        } catch (ParseException e) {
            log.error("Error in date " + e.getMessage());
            return new ResponseEntity<>(new PriceResponseDto(), HttpStatus.BAD_REQUEST);
        }
         Price price = priceService.getPriceByDateAndBrandAndProduct(
                 applicationDate, priceRequestDto.getProductId(), priceRequestDto.getBrandId()
         );
        PriceResponseDto priceResponseDto = new PriceResponseDto(
                price.getProductId(),
                price.getId(),
                price.getPrice(),
                price.getBrandId(),
                applicationDate
        );
        log.info("Price response found : " + priceResponseDto);
        return new ResponseEntity<>(
                priceResponseDto
                , HttpStatus.OK);
    }
}
