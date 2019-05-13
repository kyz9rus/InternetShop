package ru.tsystems.internetshop.util;

/**
 * This class is response object for ajax requests
 */
public class ResponseInfo {
    private int statusCode;
    private String message;

    private String descriptionErrorMessage;

    public ResponseInfo(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ResponseInfo(int statusCode, String message, String descriptionErrorMessage) {
        this.statusCode = statusCode;
        this.message = message;
        this.descriptionErrorMessage = descriptionErrorMessage;
    }

    public String getDescriptionErrorMessage() {
        return descriptionErrorMessage;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDescriptionErrorMessage(String descriptionErrorMessage) {
        this.descriptionErrorMessage = descriptionErrorMessage;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ResponseInfo)) return false;
        final ResponseInfo other = (ResponseInfo) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getStatusCode() != other.getStatusCode()) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResponseInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getStatusCode();
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "ResponseInfo(statusCode=" + this.getStatusCode() + ", message=" + this.getMessage() + ")";
    }
}
