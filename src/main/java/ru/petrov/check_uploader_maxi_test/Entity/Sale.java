package ru.petrov.check_uploader_maxi_test.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "sale")

@JacksonXmlRootElement(localName = "SALE")
public class Sale {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @JacksonXmlProperty(localName = "CARD_NUMBER")
    private Long card_number;

    @CreatedDate
    @JacksonXmlProperty(localName = "DATE")
    private Calendar date;

    @OneToMany(mappedBy = "sale_id")
    @JacksonXmlElementWrapper(localName = "PRODUCTS")
    private List<Product> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCard_number() {
        return card_number;
    }

    public void setCard_number(Long card_number) {
        this.card_number = card_number;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
