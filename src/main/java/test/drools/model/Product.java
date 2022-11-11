package test.drools.model;

public class Product {

    public static final String DIAMOND = "DIAMOND"; // 钻石
    public static final String GOLD = "GOLD"; // 黄金
    private String type;
    private int discount;


    public Product(String type, int discount) {
        this.type = type;
        this.discount = discount;
    }

    public Product() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
