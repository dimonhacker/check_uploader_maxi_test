package ru.petrov.check_uploader_maxi_test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petrov.check_uploader_maxi_test.Entity.Product;
import ru.petrov.check_uploader_maxi_test.Entity.Sale;
import ru.petrov.check_uploader_maxi_test.Repository.ProductRepository;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public List<Product> getBySale(Sale sale) {
        return productRepository.findBySale(sale.getId());
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
