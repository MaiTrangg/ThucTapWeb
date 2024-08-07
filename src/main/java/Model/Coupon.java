package Model;

//import java.math.Double;

public class Coupon {
    private int coupon_id;
    private String code;
    private String discountType;
    private Double discountValue;
    private Double maxDiscountValue;
    private Double minTotalValue;

    public Coupon( String code, String discountType, Double discountValue, Double maxDiscountValue, Double minTotalValue) {
        //this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.maxDiscountValue = maxDiscountValue;
        this.minTotalValue = minTotalValue;
    }

    public Coupon() {

    }

//    public Coupon(String discount40, String percentage, Double Double, Double Double1, Double Double2) {
//    }
// Getters and setters

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
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

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Double getMaxDiscountValue() {
        return maxDiscountValue;
    }

    public void setMaxDiscountValue(Double maxDiscountValue) {
        this.maxDiscountValue = maxDiscountValue;
    }

    public Double getMinTotalValue() {
        return minTotalValue;
    }

    public void setMinTotalValue(Double minTotalValue) {
        this.minTotalValue = minTotalValue;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "coupon_id=" + coupon_id +
                ", code='" + code + '\'' +
                ", discountType='" + discountType + '\'' +
                ", discountValue=" + discountValue +
                ", maxDiscountValue=" + maxDiscountValue +
                ", minTotalValue=" + minTotalValue +
                '}';
    }
}