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

    private List<Product> findMatchingProductsFromRequest(List<SalesRequestProductItems> productItems) {
        List<Product> products = new ArrayList<>();
        for (SalesRequestProductItems item : productItems) {
            final Product p = productDatabase.retrieve(item.getId());
            if (p != null) {
                products.add(p);
            }
        }
        return products;
    }

    private float getFixedDiscountTotal(SalesRequest request) {

        float fixedDiscountTotal = 0.0f;
        for (String discountCode : request.getDiscountCodes()) {
            final Discount discount = discounts.retrieveDiscount(discountCode);
            if (discount != null && discount.getDiscountUnit().equals(DiscountUnitEnum.FIXED)) {
                fixedDiscountTotal += discount.getDiscount();
            }
        }
        return fixedDiscountTotal;
    }

    private float getPercentDiscountTotal(SalesRequest request) {

        float percentDiscountTotal = 0.0f;
        for (String discountCode : request.getDiscountCodes()) {
            final Discount discount = discounts.retrieveDiscount(discountCode);
            if (discount != null && discount.getDiscountUnit().equals(DiscountUnitEnum.PERCENT)) {
                percentDiscountTotal += discount.getDiscount();
            }
        }
        return percentDiscountTotal;
    }

    private List<SalesResponseAppliedDiscounts> getAppliedDiscounts(SalesRequest request) {
        List<SalesResponseAppliedDiscounts> appliedDiscounts = new ArrayList<>();
        for (String discountCode : request.getDiscountCodes()) {
            SalesResponseAppliedDiscounts appliedDiscount = null;
            final Discount discount = discounts.retrieveDiscount(discountCode);
            if (discount != null) {
                appliedDiscount = new SalesResponseAppliedDiscounts();
                appliedDiscount.setCode(discountCode);
                appliedDiscount.setDiscount(discount.getDiscount());
                appliedDiscount.setDiscountUnit(discount.getDiscountUnit());
                appliedDiscounts.add(appliedDiscount);
            }
        }
        return appliedDiscounts;
    }

    private List<SalesResponseItems> buildSaleItemsWithAppliedDiscounts(List<Product> products, SalesRequest request,
            float uniformDiscount, float percentDiscountTotal) {

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
        return saleItems;
    }

    private SalesResponseSalesSummary buildSalesSummary(List<SalesResponseItems> saleItems) {
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

        return salesSummary;

    }

    /*
     * TODO: lots of good junit opportunities here
     */

    public SalesResponse processSalesRequest(SalesRequest request) {

        final SalesResponse salesResponse = new SalesResponse();

        /*
         * Cross-Reference the product items in the request with the product database
         */

        List<Product> products = findMatchingProductsFromRequest(request.getProductItems());

        /*
         * Determine total of all "fixed" discounts and apply uniformly to all products
         */

        float fixedDiscountTotal = getFixedDiscountTotal(request);
        float percentDiscountTotal = getPercentDiscountTotal(request);
        float uniformDiscount = 0.0f;

        salesResponse.setAppliedDiscounts(getAppliedDiscounts(request));

        if (products.size() > 0) {
            /*
             * TODO: What happens when the applied uniform discount > than the price of the
             * product?
             */
            uniformDiscount = fixedDiscountTotal / products.size();
        }

        final List<SalesResponseItems> saleItems = buildSaleItemsWithAppliedDiscounts(products, request,
                uniformDiscount,
                percentDiscountTotal);

        salesResponse.setItems(saleItems);

        salesResponse.setSalesSummary(buildSalesSummary(saleItems));

        return salesResponse;

    }

}
