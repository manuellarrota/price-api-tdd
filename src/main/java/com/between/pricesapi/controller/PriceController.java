package com.between.pricesapi.controller;

import com.between.pricesapi.dto.PriceRequestDto;
import com.between.pricesapi.dto.PriceResponseDto;
import com.between.pricesapi.model.Price;
import com.between.pricesapi.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "price", description = "Price API")
@RestController
@RequestMapping("price")
@Slf4j
public class PriceController {
    private final PriceService priceService;
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }
    @Operation(summary = "Get final price by date, brand, and product",
            description = "Returns the final price of a product for a given date, brand, and product ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The request object containing the application date, product ID, and brand ID.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = PriceRequestDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - The price was found and returned."),
                    @ApiResponse(responseCode = "400", description = "Bad Request - The request is invalid."),
                    @ApiResponse(responseCode = "404", description = "Not Found - The price was not found.")
            })
    @PostMapping
    public ResponseEntity<PriceResponseDto> getPriceByDateAndBrandAndProduct(@RequestBody PriceRequestDto priceRequestDto ) {
        if(!validate(priceRequestDto)){
            return new ResponseEntity<>(new PriceResponseDto(), HttpStatus.BAD_REQUEST);
        }
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
        if (price == null){
            log.info("Not found by date: " + applicationDate);
            return new ResponseEntity<>(new PriceResponseDto(), HttpStatus.NOT_FOUND);
        }
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

    boolean validate(PriceRequestDto priceRequestDto){
        if(priceRequestDto.getBrandId() == null || priceRequestDto.getBrandId() == 0 ){
            log.error("brand is empty. ");
            return false;
        }
        if(priceRequestDto.getProductId() == null || priceRequestDto.getProductId() == 0 ){
            log.error("product is empty. ");
            return false;
        }
        if(priceRequestDto.getApplicationDate() == null || priceRequestDto.getApplicationDate().isEmpty() ){
            log.error("date is empty. ");
            return false;
        }
        return true;
    }
}
