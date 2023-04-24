package com.between.pricesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequestDto {
    private String applicationDate;
    private Long productId;
    private Integer brandId;
}
