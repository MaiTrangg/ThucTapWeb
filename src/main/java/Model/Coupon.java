package Model;

import java.math.BigDecimal;

public class Coupon {
    private int id;
    private String code;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal maxDiscountValue;
    private BigDecimal minTotalValue;

    public Coupon( String code, String discountType, BigDecimal discountValue, BigDecimal maxDiscountValue, BigDecimal minTotalValue) {
        //this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.maxDiscountValue = maxDiscountValue;
        this.minTotalValue = minTotalValue;
    }

    public Coupon() {

    }

//    public Coupon(String discount40, String percentage, BigDecimal bigDecimal, BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
//    }
// Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getMaxDiscountValue() {
        return maxDiscountValue;
    }

    public void setMaxDiscountValue(BigDecimal maxDiscountValue) {
        this.maxDiscountValue = maxDiscountValue;
    }

    public BigDecimal getMinTotalValue() {
        return minTotalValue;
    }

    public void setMinTotalValue(BigDecimal minTotalValue) {
        this.minTotalValue = minTotalValue;
    }
}
