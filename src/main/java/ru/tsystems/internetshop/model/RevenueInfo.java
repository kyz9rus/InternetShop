package ru.tsystems.internetshop.model;

/**
 * This class contains 2 fields and contains information about revenue (for week and month)
 */
public class RevenueInfo {
    private Long revenueForWeek;
    private Long revenueForMonth;

    public RevenueInfo(Long revenueForWeek, Long revenueForMonth) {
        this.revenueForWeek = revenueForWeek;
        this.revenueForMonth = revenueForMonth;
    }

    public Long getRevenueForWeek() {
        return this.revenueForWeek;
    }

    public Long getRevenueForMonth() {
        return this.revenueForMonth;
    }

    public void setRevenueForWeek(Long revenueForWeek) {
        this.revenueForWeek = revenueForWeek;
    }

    public void setRevenueForMonth(Long revenueForMonth) {
        this.revenueForMonth = revenueForMonth;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RevenueInfo)) return false;
        final RevenueInfo other = (RevenueInfo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$revenueForWeek = this.getRevenueForWeek();
        final Object other$revenueForWeek = other.getRevenueForWeek();
        if (this$revenueForWeek == null ? other$revenueForWeek != null : !this$revenueForWeek.equals(other$revenueForWeek))
            return false;
        final Object this$revenueForMonth = this.getRevenueForMonth();
        final Object other$revenueForMonth = other.getRevenueForMonth();
        if (this$revenueForMonth == null ? other$revenueForMonth != null : !this$revenueForMonth.equals(other$revenueForMonth))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RevenueInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $revenueForWeek = this.getRevenueForWeek();
        result = result * PRIME + ($revenueForWeek == null ? 43 : $revenueForWeek.hashCode());
        final Object $revenueForMonth = this.getRevenueForMonth();
        result = result * PRIME + ($revenueForMonth == null ? 43 : $revenueForMonth.hashCode());
        return result;
    }

    public String toString() {
        return "RevenueInfo(revenueForWeek=" + this.getRevenueForWeek() + ", revenueForMonth=" + this.getRevenueForMonth() + ")";
    }
}
