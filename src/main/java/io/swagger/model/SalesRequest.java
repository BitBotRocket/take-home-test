package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.SalesRequestProductItems;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SalesRequest
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-08T23:47:40.241076144Z[GMT]")

public class SalesRequest {
  @JsonProperty("discountCodes")
  @Valid
  private List<String> discountCodes = null;
  @JsonProperty("productItems")
  @Valid
  private List<SalesRequestProductItems> productItems = null;

  public SalesRequest discountCodes(List<String> discountCodes) {

    this.discountCodes = discountCodes;
    return this;
  }

  public SalesRequest addDiscountCodesItem(String discountCodesItem) {
    if (this.discountCodes == null) {
      this.discountCodes = new ArrayList<String>();
    }
    this.discountCodes.add(discountCodesItem);
    return this;
  }

  /**
   * Get discountCodes
   * 
   * @return discountCodes
   **/

  @Schema(description = "")

  public List<String> getDiscountCodes() {
    return discountCodes;
  }

  public void setDiscountCodes(List<String> discountCodes) {
    this.discountCodes = discountCodes;
  }

  public SalesRequest productItems(List<SalesRequestProductItems> productItems) {

    this.productItems = productItems;
    return this;
  }

  public SalesRequest addProductItemsItem(SalesRequestProductItems productItemsItem) {
    if (this.productItems == null) {
      this.productItems = new ArrayList<SalesRequestProductItems>();
    }
    this.productItems.add(productItemsItem);
    return this;
  }

  /**
   * Get productItems
   * 
   * @return productItems
   **/

  @Schema(description = "")
  @Valid
  public List<SalesRequestProductItems> getProductItems() {
    return productItems;
  }

  public void setProductItems(List<SalesRequestProductItems> productItems) {
    this.productItems = productItems;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalesRequest salesRequest = (SalesRequest) o;
    return Objects.equals(this.discountCodes, salesRequest.discountCodes) &&
        Objects.equals(this.productItems, salesRequest.productItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(discountCodes, productItems);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalesRequest {\n");

    sb.append("    discountCodes: ").append(toIndentedString(discountCodes)).append("\n");
    sb.append("    productItems: ").append(toIndentedString(productItems)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
