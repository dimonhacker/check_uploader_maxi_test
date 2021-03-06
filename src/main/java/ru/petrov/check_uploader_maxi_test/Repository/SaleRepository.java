package ru.petrov.check_uploader_maxi_test.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.petrov.check_uploader_maxi_test.Entity.Sale;

import java.util.List;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {


    List<Sale> findByCardNumber(Long cardNumber);

}
