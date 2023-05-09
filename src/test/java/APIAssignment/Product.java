package APIAssignment;

public class Product {

    public Product() {
    }

    public String productId;
    public String productName;
    public String productPrice;
    public String productBrand;

    public void getProductId() {
        System.out.println("Id=" + productId);
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void getProductName() {
        System.out.println("Product Name=" + productName);
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void getProductPrice() {
        System.out.println("Price=" + productPrice);
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void getProductBrand() {
        System.out.println("Brand=" + productBrand);
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }
}