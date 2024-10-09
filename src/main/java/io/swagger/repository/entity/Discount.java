package io.swagger.repository.entity;

import io.swagger.model.SalesResponseAppliedDiscounts.DiscountUnitEnum;

public final class Discount {
    private String name;
    private DiscountUnitEnum discountUnit;
    private float discount;

    public Discount(String name, DiscountUnitEnum discountUnit, float discount) {
        this.name = name;
        this.discountUnit = discountUnit;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public DiscountUnitEnum getDiscountUnit() {
        return discountUnit;
    }

    public float getDiscount() {
        return discount;
    }
}
