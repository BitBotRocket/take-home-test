package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.SalesResponseAppliedDiscounts;
import io.swagger.model.SalesResponseItems;
import io.swagger.model.SalesResponseSalesSummary;
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
 * SalesResponse
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-09T02:10:56.981355728Z[GMT]")


public class SalesResponse   {
  @JsonProperty("appliedDiscounts")
  @Valid
  private List<SalesResponseAppliedDiscounts> appliedDiscounts = null;
  @JsonProperty("items")
  @Valid
  private List<SalesResponseItems> items = null;
  @JsonProperty("salesSummary")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private SalesResponseSalesSummary salesSummary = null;


  public SalesResponse appliedDiscounts(List<SalesResponseAppliedDiscounts> appliedDiscounts) { 

    this.appliedDiscounts = appliedDiscounts;
    return this;
  }

  public SalesResponse addAppliedDiscountsItem(SalesResponseAppliedDiscounts appliedDiscountsItem) {
    if (this.appliedDiscounts == null) {
      this.appliedDiscounts = new ArrayList<SalesResponseAppliedDiscounts>();
    }
    this.appliedDiscounts.add(appliedDiscountsItem);
    return this;
  }

  /**
   * Get appliedDiscounts
   * @return appliedDiscounts
   **/
  
  @Schema(description = "")
  @Valid
  public List<SalesResponseAppliedDiscounts> getAppliedDiscounts() {  
    return appliedDiscounts;
  }



  public void setAppliedDiscounts(List<SalesResponseAppliedDiscounts> appliedDiscounts) { 
    this.appliedDiscounts = appliedDiscounts;
  }

  public SalesResponse items(List<SalesResponseItems> items) { 

    this.items = items;
    return this;
  }

  public SalesResponse addItemsItem(SalesResponseItems itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<SalesResponseItems>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
   **/
  
  @Schema(description = "")
  @Valid
  public List<SalesResponseItems> getItems() {  
    return items;
  }



  public void setItems(List<SalesResponseItems> items) { 
    this.items = items;
  }

  public SalesResponse salesSummary(SalesResponseSalesSummary salesSummary) { 

    this.salesSummary = salesSummary;
    return this;
  }

  /**
   * Get salesSummary
   * @return salesSummary
   **/
  
  @Schema(description = "")
  
@Valid
  public SalesResponseSalesSummary getSalesSummary() {  
    return salesSummary;
  }



  public void setSalesSummary(SalesResponseSalesSummary salesSummary) { 
    this.salesSummary = salesSummary;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalesResponse salesResponse = (SalesResponse) o;
    return Objects.equals(this.appliedDiscounts, salesResponse.appliedDiscounts) &&
        Objects.equals(this.items, salesResponse.items) &&
        Objects.equals(this.salesSummary, salesResponse.salesSummary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appliedDiscounts, items, salesSummary);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalesResponse {\n");
    
    sb.append("    appliedDiscounts: ").append(toIndentedString(appliedDiscounts)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    salesSummary: ").append(toIndentedString(salesSummary)).append("\n");
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
