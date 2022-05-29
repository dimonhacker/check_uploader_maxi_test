package ru.petrov.check_uploader_maxi_test.Service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.petrov.check_uploader_maxi_test.Entity.Product;
import ru.petrov.check_uploader_maxi_test.Entity.Sale;
import ru.petrov.check_uploader_maxi_test.Jackson.Sales;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;


@Component
public class LoadService {

    @Autowired
    SaleService saleService;

    @Autowired
    ProductService productService;

    @Value("${papka_maxi}")
    private String filePath;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void loadFromFile() {
        try {
            XMLInputFactory f = XMLInputFactory.newFactory();
            XMLStreamReader sr = f.createXMLStreamReader(new FileInputStream(filePath));
            XmlMapper mapper = new XmlMapper();
            Sales sales = mapper.readValue(sr, Sales.class);
            sr.close();
            for (Sale s : sales.getSales()) {
                saleService.saveSale(s);
                for (Product p : s.getProducts()) {
                    p.setSale_id(s);
                    productService.saveProduct(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
