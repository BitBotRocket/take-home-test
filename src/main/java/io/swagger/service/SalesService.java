package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.repository.DiscountDatabase;
import io.swagger.repository.ProductDatabase;
import io.swagger.model.SalesRequest;
import io.swagger.model.SalesResponse;
import io.swagger.model.SalesResponseAppliedDiscounts;
import io.swagger.model.SalesResponseItems;
import io.swagger.model.SalesResponseSalesSummary;
import io.swagger.model.SalesResponseAppliedDiscounts.DiscountUnitEnum;

import java.util.List;
import java.math.BigDecimal;
import io.swagger.model.Product;
import io.swagger.model.Products;
import java.util.ArrayList;
import io.swagger.model.SalesRequestProductItems;
import io.swagger.repository.entity.Discount;

@Service
public class SalesService {

    private final ProductDatabase productDatabase;

    private final DiscountDatabase discounts;

    @Autowired
    public SalesService(ProductDatabase productDatabase, DiscountDatabase discounts) {
        this.productDatabase = productDatabase;
        this.discounts = discounts;
    }

    /*
     * TODO: lots of good junit opportunities here
     */

    public SalesResponse processSalesRequest(SalesRequest request) {

        final SalesResponse salesResponse = new SalesResponse();

        /*
         * Cross-Reference the product items in the request with the product database
         */

        List<Product> products = new ArrayList<>();

        /*
         * TODO: What happens when the product is not found in the database?
         * What happens when the product is found but the price is 0.0?
         * What happens when the product is found but the price is negative?
         * What should the default response be if the cart is empty?
         * Need to test for no applied discounts and no products in the cart
         * Need to test for no applied discounts and products in the cart
         */

        for (SalesRequestProductItems item : request.getProductItems()) {
            final Product p = productDatabase.retrieve(item.getId());
            if (p != null) {
                products.add(p);
            }
        }

        /*
         * Determine total of all "fixed" discounts and apply uniformly to all products
         */

        float fixedDiscountTotal = 0.0f;
        float percentDiscountTotal = 0.0f;
        float uniformDiscount = 0.0f;

        List<SalesResponseAppliedDiscounts> appliedDiscounts = new ArrayList<>();

        for (String discountCode : request.getDiscountCodes()) {
            System.out.println("discountCode = " + discountCode);

            SalesResponseAppliedDiscounts appliedDiscount = null;

            final Discount discount = discounts.retrieveDiscount(discountCode);

            if (discount != null) {
                appliedDiscount = new SalesResponseAppliedDiscounts();
                appliedDiscount.setCode(discountCode);
                appliedDiscount.setDiscount(discount.getDiscount());
                appliedDiscount.setDiscountUnit(discount.getDiscountUnit());
                appliedDiscounts.add(appliedDiscount);
            }

            if (discount != null && discount.getDiscountUnit().equals(DiscountUnitEnum.FIXED)) {
                fixedDiscountTotal += discount.getDiscount();
            } else if (discount != null && discount.getDiscountUnit().equals(DiscountUnitEnum.PERCENT)) {
                percentDiscountTotal += discount.getDiscount();
            } else {
                System.out.println("Discount not found or not fixed with code = " + discountCode);
            }
        }

        salesResponse.setAppliedDiscounts(appliedDiscounts);

        if (products.size() > 0) {
            /*
             * TODO: What happens when the applied uniform discount > than the price of the
             * product?
             */
            uniformDiscount = fixedDiscountTotal / products.size();
        }

        System.out.println("Uniform discount = " + uniformDiscount);
        System.out.println("Total percent discount = " + percentDiscountTotal);

        /*
         * For each product apply the discounts
         */

        List<SalesResponseItems> saleItems = new ArrayList<>();


        for (SalesRequestProductItems item : request.getProductItems()) {

            final Product p = productDatabase.retrieve(item.getId());

            if (p != null) {

                SalesResponseItems salesResponseItem = new SalesResponseItems();

                float price = p.getPrice();
                float quantity = item.getQuantity();
                float discount = 0.0f;
                float totalBeforeTax = price * quantity;
    
                if (uniformDiscount > 0.0f) {
                    discount += uniformDiscount;
                }
    
                if (percentDiscountTotal > 0.0f) {
                    discount += price * quantity * percentDiscountTotal;
                }
    
                totalBeforeTax -= discount;
    
                salesResponseItem.setAppliedDiscount(discount);
                salesResponseItem.setUnitPrice(price);
                salesResponseItem.setId(p.getId());
                salesResponseItem.setQuantity(new BigDecimal(quantity));
                salesResponseItem.setTotalBeforeTax(totalBeforeTax);
    
                saleItems.add(salesResponseItem);

            }
        }

        salesResponse.setItems(saleItems);

        float totalBeforeTax = 0.0f;
        for (SalesResponseItems item : saleItems) {
            totalBeforeTax += item.getTotalBeforeTax();
        }

        SalesResponseSalesSummary salesSummary = new SalesResponseSalesSummary();

        // TODO: Make applied tax percentage configurable
        salesSummary.setTotalBeforeTaxes(totalBeforeTax);
        salesSummary.setTaxRate(0.13f);
        salesSummary.setTaxes(totalBeforeTax * 0.13f);
        salesSummary.setTotalAfterTaxes(totalBeforeTax * 1.13f);

        salesResponse.setSalesSummary(salesSummary);

        /*
         * TODO: refactor into smaller methods
         */

        return salesResponse;

    }

}
