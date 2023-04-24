package com.between.pricesapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequestDto {

    private String applicationDate;
    private Long productId;
    private Integer brandId;
}
