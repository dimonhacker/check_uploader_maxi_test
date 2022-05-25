package ru.petrov.check_uploader_maxi_test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petrov.check_uploader_maxi_test.Entity.Sale;
import ru.petrov.check_uploader_maxi_test.Repository.SaleRepository;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    public Sale getSale(){
        return saleRepository.findById(0L).orElse(null);
    }
    public List<Sale> getAll(){
        return (List<Sale>) saleRepository.findAll();
    }
}
