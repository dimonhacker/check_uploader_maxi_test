package ru.petrov.check_uploader_maxi_test.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.petrov.check_uploader_maxi_test.Entity.Product;

import java.util.Calendar;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {


    @Query(value = "Select * from product where sale_id = :id", nativeQuery = true)
    List<Product> findBySale(long id);

    @Query(value = "select id, price, product_code, sale_id, name, sum(count) as count from product where sale_id in (select sale.id from sale where card_number= :cardNumber)  GROUP by product_code ORDER by count desc limit 3", nativeQuery = true)
    List<Product> findTopBySale(long cardNumber);

    @Query(value = "select sum(product.count*product.price) from product where product.sale_id in ( select id from sale where to_days(sale.date) = to_days(:date)) ", nativeQuery = true)
    Double getSumOfTheDay(Calendar date);
}
