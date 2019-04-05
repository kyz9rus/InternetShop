package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

@Data
public class CouponDTO {
    private Integer id;
    private String name;
    private String value;
    private String description;
}
