package ru.petrov.check_uploader_maxi_test.Controller;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.petrov.check_uploader_maxi_test.Entity.Product;
import ru.petrov.check_uploader_maxi_test.Entity.Sale;
import ru.petrov.check_uploader_maxi_test.Jackson.Sales;
import ru.petrov.check_uploader_maxi_test.Service.ProductService;
import ru.petrov.check_uploader_maxi_test.Service.SaleService;

import javax.xml.stream.*;
import java.io.*;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    SaleService saleService;

    @Value("${papka_maxi}")
    private String filePath;

    @GetMapping("/")
    public String indexPage(Model model) {
        List<Sale> sales = saleService.getAll();
        model.addAttribute("products", productService.getBySale(sales.get(0)));
        return "index";
    }
    @GetMapping("/load")
    public String loadPage(Model model) {
        try {
            XMLInputFactory f = XMLInputFactory.newFactory();
            XMLStreamReader sr = f.createXMLStreamReader(new FileInputStream(filePath));
            XmlMapper mapper = new XmlMapper();
            Sales sales = mapper.readValue(sr, Sales.class);
            for (Sale s : sales.getSales()) {
                saleService.saveSale(s);
                for (Product p : s.getProducts()) {
                    p.setSale_id(s);
                    productService.saveProduct(p);
                }
            }
            sr.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return "index";
    }
}
