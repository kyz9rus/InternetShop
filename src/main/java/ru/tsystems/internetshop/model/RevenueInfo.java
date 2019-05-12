package ru.tsystems.internetshop.model;

import lombok.Data;

/**
 * This class contains 2 fields and contains information about revenue (for week and month)
 */
@Data
public class RevenueInfo {
    private Long revenueForWeek;
    private Long revenueForMonth;

    public RevenueInfo(Long revenueForWeek, Long revenueForMonth) {
        this.revenueForWeek = revenueForWeek;
        this.revenueForMonth = revenueForMonth;
    }
}
