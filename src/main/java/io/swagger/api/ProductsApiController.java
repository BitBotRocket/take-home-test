package io.swagger.api;

import java.math.BigDecimal;
import io.swagger.model.CreateProductRequest;
import io.swagger.model.CreateProductResponse;
import io.swagger.model.Product;
import io.swagger.model.Products;
import io.swagger.repository.ProductDatabase;
import io.swagger.service.ProductService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-08T19:58:21.879000852Z[GMT]")
@RestController
public class ProductsApiController implements ProductsApi {

    private static final Logger log = LoggerFactory.getLogger(ProductsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final ProductService productService;

    @org.springframework.beans.factory.annotation.Autowired
    public ProductsApiController(ObjectMapper objectMapper, HttpServletRequest request, ProductService productService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.productService = productService;
    }

    public ResponseEntity<Void> clearAllProducts() {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            productService.clearAllProducts();
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<CreateProductResponse> createProduct(
            @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody CreateProductRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            System.out.println("Storing product: " + body);

            CreateProductResponse response = productService.createProduct(body);

            if (response != null) {
                return new ResponseEntity<CreateProductResponse>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<CreateProductResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CreateProductResponse>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Products> getAllProducts(
            @Parameter(in = ParameterIn.QUERY, description = "type of text base filter to apply to product name", schema = @Schema(allowableValues = {
                    "exact", "prefix", "postfix",
                    "wildcard" }, defaultValue = "wildcard")) @Valid @RequestParam(value = "nameFilterMode", required = false, defaultValue = "wildcard") String nameFilterMode,
            @Parameter(in = ParameterIn.QUERY, description = "ignore case", schema = @Schema(defaultValue = "true")) @Valid @RequestParam(value = "ignoreCase", required = false, defaultValue = "true") Boolean ignoreCase,
            @Parameter(in = ParameterIn.QUERY, description = "number of products to return per page", schema = @Schema(defaultValue = "0")) @Valid @RequestParam(value = "size", required = false, defaultValue = "100") BigDecimal size,
            @Parameter(in = ParameterIn.QUERY, description = "starting page index to retrieve", schema = @Schema(defaultValue = "100")) @Valid @RequestParam(value = "page", required = false, defaultValue = "0") BigDecimal page,
            @Parameter(in = ParameterIn.QUERY, description = "filter by product id", schema = @Schema()) @Valid @RequestParam(value = "productId", required = false) String productId,
            @Parameter(in = ParameterIn.QUERY, description = "type of text base filter to apply", schema = @Schema(allowableValues = {
                    "gt", "lt", "gte", "lte",
                    "eq" })) @Valid @RequestParam(value = "priceComparisonMode", required = false) String priceComparisonMode,
            @Parameter(in = ParameterIn.QUERY, description = "find by price equality", schema = @Schema()) @Valid @RequestParam(value = "price", required = false) Float price) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            /*
             * TODO: Add additional query parameters to filter the products
             */

            Products products = productService.retrieveAllProducts();

            if (products != null) {
                return new ResponseEntity<Products>(products, HttpStatus.OK);
            } else {
                return new ResponseEntity<Products>(products, HttpStatus.NOT_FOUND);
            }

        }

        return new ResponseEntity<Products>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Product> getProductById(
            @Parameter(in = ParameterIn.PATH, description = "product identifier", required = true, schema = @Schema()) @PathVariable("productId") String productId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            Product product = productService.retrieveProductById(productId);

            if (product != null) {
                return new ResponseEntity<Product>(product, HttpStatus.OK);
            } else {
                return new ResponseEntity<Product>(product, HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);

    }

}
