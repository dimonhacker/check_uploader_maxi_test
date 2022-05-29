package ru.petrov.check_uploader_maxi_test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petrov.check_uploader_maxi_test.Entity.Product;
import ru.petrov.check_uploader_maxi_test.Entity.Sale;
import ru.petrov.check_uploader_maxi_test.Repository.ProductRepository;

import java.util.Calendar;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public List<Product> getTop3(Long cardNumber) {
        return productRepository.findTopBySale(cardNumber);
    }

    public void saveAllProduct(List<Product> productList, Sale sale) {
        for (Product p : productList) {
            p.setSale_id(sale);
        }
        productRepository.saveAll(productList);
    }

    public Double getSumOfTheDay(Calendar calendar) {
        Double sum = productRepository.getSumOfTheDay(calendar);
        if (sum == null) sum = 0d;
        return sum;
    }
}
