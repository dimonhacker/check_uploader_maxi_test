package ru.petrov.check_uploader_maxi_test.Entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;

@Entity
@Table(name = "product",
        indexes = {
                @Index(name = "sale_id", columnList = "sale_id"),
                @Index(name = "id", columnList = "id")
        }
)
@JacksonXmlRootElement
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JacksonXmlProperty(localName = "PRODUCT_CODE")
    private String productCode;

    @Column
    @JacksonXmlProperty(localName = "NAME")
    private String name;

    @Column
    @JacksonXmlProperty(localName = "PRICE")
    private String price;

    @Column
    @JacksonXmlProperty(localName = "COUNT")
    private short count;

    @JoinColumn(name = "sale_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Sale sale_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public short getCount() {
        return count;
    }

    public void setCount(short count) {
        this.count = count;
    }

    public Sale getSale_id() {
        return sale_id;
    }

    public void setSale_id(Sale sale_id) {
        this.sale_id = sale_id;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", count=" + count +
                ", sale_id=" + sale_id +
                '}';
    }
}
