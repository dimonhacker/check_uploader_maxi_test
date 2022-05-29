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

    public void saveSale(Sale sale) {
        saleRepository.save(sale);
    }

    public List<Sale> findByCardNumber(Long card_number) {
        List<Sale> s;
        s = saleRepository.findByCardNumber(card_number);
        return s;
    }
}
