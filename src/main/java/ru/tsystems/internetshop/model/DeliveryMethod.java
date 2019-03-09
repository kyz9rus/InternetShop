package ru.tsystems.internetshop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "deliveryMethod")
@Data
public class DeliveryMethod {
    @Id
    private DeliveryType deliverType;
    private int price;
}
