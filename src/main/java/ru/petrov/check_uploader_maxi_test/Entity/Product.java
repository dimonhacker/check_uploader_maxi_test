package ru.petrov.check_uploader_maxi_test.Entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;

@Entity
@Table(name = "product")
@JacksonXmlRootElement
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @JacksonXmlProperty(localName = "PRODUCT_CODE")
    private String product_code;

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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Sale sale_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
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
}
