package ru.tsystems.internetshop.model;

import lombok.Data;

@Data
public class RevenueInfo {
    private Long revenueForWeek;
    private Long revenueForMonth;

    public RevenueInfo(Long revenueForWeek, Long revenueForMonth) {
        this.revenueForWeek = revenueForWeek;
        this.revenueForMonth = revenueForMonth;
    }
}
