package io.swagger.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import io.swagger.model.Product;
import io.swagger.model.SalesResponseAppliedDiscounts.DiscountUnitEnum;

import org.springframework.stereotype.Component;
import io.swagger.repository.entity.Discount;

@Component
public class DiscountDatabase {

    /*
     * TODO: Load up discounts from a static file within our fat-jar, or just
     * hard-code for now
     */

    private final Map<String, Discount> database = new ConcurrentHashMap<>();

    public DiscountDatabase() {
        database.put("BACK-TO-SCHOOL", new Discount("BACK-TO-SCHOOL", DiscountUnitEnum.PERCENT, 0.10f));
        database.put("EMPLOYEE-DISCOUNT", new Discount("EMPLOYEE-DISCOUNT", DiscountUnitEnum.FIXED, 10.00f));
    }

    public Discount retrieveDiscount(String key) {
        if (key != null) {
            return database.get(key);
        } else {
            return null;
        }
    }

    public List<Discount> retrieveAllDiscounts() {
        return new ArrayList<>(database.values());
    }

}
