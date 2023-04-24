package com.between.pricesapi.controller;

import com.between.pricesapi.dto.PriceRequestDto;
import com.between.pricesapi.dto.PriceResponseDto;
import com.between.pricesapi.model.Price;
import com.between.pricesapi.service.PriceService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
public class PriceControllerTest {
    @Mock
    private PriceService priceService;
    private PriceController priceController;
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    private final Integer brandId = 1;
    private final Long productId = 35455L;
    private final String applicationDateString = "2020-00-14 10:00:00";
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        priceController = new PriceController(priceService);
        Date startDatePrice;
        Date endDatePrice;
        String currency = "EUR";
        String applicationDateString = "2020-06-14 10:00:00";
        String startDatePriceString = "2020-06-14 10:00:00";
        String endDatePriceString = "2020-06-14 10:00:00";
        Date applicationDate;
        try {
            startDatePrice =  df.parse(startDatePriceString);
            endDatePrice =  df.parse(endDatePriceString);
            applicationDate =  df.parse(applicationDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Price price = Price.builder().id(1L).productId(productId).
                startDate(startDatePrice).
                endDate(endDatePrice).
                currency(currency).
                priority(0).
                price(35.50).
                brandId(brandId).
                build();
        Mockito.when(priceService.getPriceByDateAndBrandAndProduct(applicationDate, productId, brandId)).thenReturn(
                price
        );
    }

    @Test
    void when_all_arguments_ok_then_return_price_ok() {
        String applicationDateString = "2020-06-14 10:00:00";
        PriceRequestDto priceRequestDto = new PriceRequestDto(applicationDateString, productId, brandId);
        ResponseEntity<PriceResponseDto> priceResponseDtoResponseEntity = priceController.getPriceByDateAndBrandAndProduct(
                priceRequestDto);
        Assertions.assertThat(priceResponseDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void when_all_arguments_ok_but_date_dont_match_then_return_response_empty_ok() {
        String applicationDateString = "2020-00-14 10:00:00";
        PriceRequestDto priceRequestDto = new PriceRequestDto(applicationDateString, productId, brandId);
        ResponseEntity<PriceResponseDto> priceResponseDtoResponseEntity = priceController.getPriceByDateAndBrandAndProduct(
                priceRequestDto);
        Assertions.assertThat(priceResponseDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void when_date_not_ok_then_return_bad_request() {
        //When date is bad format
        String applicationDateString = "2020-00-14";
        PriceRequestDto priceRequestDto = new PriceRequestDto(applicationDateString, productId, brandId);
        ResponseEntity<PriceResponseDto> priceResponseDtoResponseEntity = priceController.getPriceByDateAndBrandAndProduct(priceRequestDto);
        Assertions.assertThat(priceResponseDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        //When date is empty
        applicationDateString = "";
        priceRequestDto = new PriceRequestDto(applicationDateString, productId, brandId);
        priceResponseDtoResponseEntity = priceController.getPriceByDateAndBrandAndProduct(priceRequestDto);
        Assertions.assertThat(priceResponseDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        //When date is null
        priceRequestDto = new PriceRequestDto(null, productId, brandId);
        priceResponseDtoResponseEntity = priceController.getPriceByDateAndBrandAndProduct(priceRequestDto);
        Assertions.assertThat(priceResponseDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    void when_product_dont_match_then_return_bad_request() {
        String applicationDateString = "2020-00-14 10:00:00";
        PriceRequestDto priceRequestDto = new PriceRequestDto(applicationDateString, 0L, brandId);
        ResponseEntity<PriceResponseDto> priceResponseDtoResponseEntity = priceController.getPriceByDateAndBrandAndProduct(
                priceRequestDto);
        Assertions.assertThat(priceResponseDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    void when_brand_dont_match_then_return_bad_request() {
        PriceRequestDto priceRequestDto = new PriceRequestDto(applicationDateString, productId, 0);
        ResponseEntity<PriceResponseDto> priceResponseDtoResponseEntity = priceController.getPriceByDateAndBrandAndProduct(priceRequestDto);
        Assertions.assertThat(priceResponseDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    void when_product_is_null_then_return_bad_request() {
        PriceRequestDto priceRequestDto = new PriceRequestDto(applicationDateString, null, brandId);
        ResponseEntity<PriceResponseDto> priceResponseDtoResponseEntity = priceController.getPriceByDateAndBrandAndProduct(priceRequestDto);
        Assertions.assertThat(priceResponseDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    void when_brand_is_null_return_bad_request() {
        PriceRequestDto priceRequestDto = new PriceRequestDto(applicationDateString, productId, null);
        ResponseEntity<PriceResponseDto> priceResponseDtoResponseEntity = priceController.getPriceByDateAndBrandAndProduct(priceRequestDto);
        Assertions.assertThat(priceResponseDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
