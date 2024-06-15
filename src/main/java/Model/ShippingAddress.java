package Model;

public class ShippingAddress {
    private int shippingAddressId;
    private String province;
    private String district;
    private String commune;
    private String country ;
    private String noteAddress;

    public ShippingAddress(int shippingAddressId, String province, String district, String commune, String country, String noteAddress) {
        this.shippingAddressId = shippingAddressId;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.country = country;
        this.noteAddress = noteAddress;
    }

    public int getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(int shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNoteAddress() {
        return noteAddress;
    }

    public void setNoteAddress(String noteAddress) {
        this.noteAddress = noteAddress;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "shippingAddressId='" + shippingAddressId + '\'' +
                ", province='" + province + '\'' +
                ", District='" + district + '\'' +
                ", commune='" + commune + '\'' +
                ", country='" + country + '\'' +
                ", noteAddress='" + noteAddress + '\'' +
                '}';
    }


    public String printShippingAdr() {
        return "ShippingAddress{" +
                ", province='" + province + '\'' +
                ", District='" + district + '\'' +
                ", commune='" + commune + '\'' +
                ", country='" + country + '\'' +
                ", noteAddress='" + noteAddress + '\'' +
                '}';
    }
}
