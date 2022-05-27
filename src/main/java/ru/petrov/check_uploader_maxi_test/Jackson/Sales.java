package ru.petrov.check_uploader_maxi_test.Jackson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ru.petrov.check_uploader_maxi_test.Entity.Sale;

import java.util.List;

@JacksonXmlRootElement(localName = "SALES")
public class Sales {

    @JacksonXmlProperty(localName = "SALE")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Sale> sales;

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
