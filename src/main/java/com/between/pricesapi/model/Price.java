package com.between.pricesapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Entity
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Price {

    @Id
    private Long id;
    private Date startDate;
    private Date endDate;
    private Integer priority;
    private Integer brandId;
    private Long productId;
    private Double price;
    private String currency;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()   )
            return false;
        if (this == o) return true;
        Price price = (Price) o;
        return id != null && Objects.equals(id, price.id);
    }
}
