package ru.petrov.check_uploader_maxi_test.Jackson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ru.petrov.check_uploader_maxi_test.Entity.Product;

import java.util.List;

@JacksonXmlRootElement(localName = "PRODUCTS")
public class Products {

    @JacksonXmlElementWrapper(localName = "PRODUCT")
    private List<Product> product;

    public Products() {
    }

    public Products(List<Product> product) {
        this.product = product;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
