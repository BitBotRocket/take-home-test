package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SalesResponseAppliedDiscounts
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-09T02:10:56.981355728Z[GMT]")


public class SalesResponseAppliedDiscounts   {
  @JsonProperty("code")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String code = null;

  /**
   * Gets or Sets discountUnit
   */
  public enum DiscountUnitEnum {
    FIXED("fixed"),
    
    PERCENT("percent");

    private String value;

    DiscountUnitEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DiscountUnitEnum fromValue(String text) {
      for (DiscountUnitEnum b : DiscountUnitEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("discountUnit")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private DiscountUnitEnum discountUnit = null;

  @JsonProperty("discount")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Float discount = null;


  public SalesResponseAppliedDiscounts code(String code) { 

    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   **/
  
  @Schema(description = "")
  
  public String getCode() {  
    return code;
  }



  public void setCode(String code) { 
    this.code = code;
  }

  public SalesResponseAppliedDiscounts discountUnit(DiscountUnitEnum discountUnit) { 

    this.discountUnit = discountUnit;
    return this;
  }

  /**
   * Get discountUnit
   * @return discountUnit
   **/
  
  @Schema(description = "")
  
  public DiscountUnitEnum getDiscountUnit() {  
    return discountUnit;
  }



  public void setDiscountUnit(DiscountUnitEnum discountUnit) { 
    this.discountUnit = discountUnit;
  }

  public SalesResponseAppliedDiscounts discount(Float discount) { 

    this.discount = discount;
    return this;
  }

  /**
   * Get discount
   * @return discount
   **/
  
  @Schema(description = "")
  
  public Float getDiscount() {  
    return discount;
  }



  public void setDiscount(Float discount) { 
    this.discount = discount;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalesResponseAppliedDiscounts salesResponseAppliedDiscounts = (SalesResponseAppliedDiscounts) o;
    return Objects.equals(this.code, salesResponseAppliedDiscounts.code) &&
        Objects.equals(this.discountUnit, salesResponseAppliedDiscounts.discountUnit) &&
        Objects.equals(this.discount, salesResponseAppliedDiscounts.discount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, discountUnit, discount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalesResponseAppliedDiscounts {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    discountUnit: ").append(toIndentedString(discountUnit)).append("\n");
    sb.append("    discount: ").append(toIndentedString(discount)).append("\n");
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
