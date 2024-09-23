package com.emazon.stock.application.dto;

public class PriceDto {
    private Double totalPrice;

    public PriceDto(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}