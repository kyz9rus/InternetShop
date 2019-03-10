package ru.tsystems.internetshop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public enum DeliveryMethod {
    postOfRussia, homeDelivery, avonServiceCenters
}
