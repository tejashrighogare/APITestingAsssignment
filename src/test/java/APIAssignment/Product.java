package APIAssignment;

public class Product {
    public String productId;
    public String productName;
    public String productPrice;
    public String productBrand;

    public Product(String productListId, String productListName, String productListPrice, String productListBrand) {
        productId = productListId;
        productName = productListName;
        productPrice = productListPrice;
        productBrand = productListBrand;
    }
}
