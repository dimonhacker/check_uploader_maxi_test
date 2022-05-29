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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JacksonXmlProperty(localName = "CARD_NUMBER")
    private Long cardNumber;

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

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
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

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", date=" + date +
                ", products=" + products +
                '}';
    }
}
