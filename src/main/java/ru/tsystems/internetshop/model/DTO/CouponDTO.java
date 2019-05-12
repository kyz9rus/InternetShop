package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

/**
 * This class is DTO for coupon entity
 */
@Data
public class CouponDTO {
    private Integer id;
    private String value;
    private String description;
}
