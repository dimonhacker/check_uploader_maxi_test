package ru.petrov.check_uploader_maxi_test.Service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.petrov.check_uploader_maxi_test.Entity.Sale;
import ru.petrov.check_uploader_maxi_test.Jackson.Sales;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LoadService {

    @Autowired
    SaleService saleService;

    @Autowired
    ProductService productService;

    @Value("${papka_maxi}")
    private String filePath;

    Logger logger = Logger.getLogger(LoadService.class.getName());

    public void loadFromFile(String file) {
        try {
            XMLInputFactory f = XMLInputFactory.newFactory();
            XMLStreamReader sr = f.createXMLStreamReader(new FileInputStream(file));
            XmlMapper mapper = new XmlMapper();
            Sales sales = mapper.readValue(sr, Sales.class);
            sr.close();
            for (Sale s : sales.getSales()) {
                try {
                    saleService.saveSale(s);
                    productService.saveAllProduct(s.getProducts(), s);
                } catch (org.springframework.dao.DataIntegrityViolationException ex) {
                    if (ex.getMessage().contains("Duplicate entry")) {
                        Logger.getLogger(LoadService.class.getName()).log(Level.WARNING, ex.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void checkPath() {
        Path batchPath = Paths.get(filePath);
        boolean flag = Files.isDirectory(batchPath);
        if (!flag) {
            System.out.println("Not a directory");
            return;
        }
        File[] files = batchPath.toFile().listFiles();
        for (File f : files) {
            try {
                if (f.isDirectory()) continue;
                loadFromFile(f.getAbsolutePath());
                logger.log(Level.INFO, "Loaded and can be removed: " + f.getAbsolutePath());

            } catch (Exception ex) {
                logger.log(Level.WARNING, ex.getMessage());
            }
        }
    }


}
