package ru.petrov.check_uploader_maxi_test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.petrov.check_uploader_maxi_test.Entity.Product;
import ru.petrov.check_uploader_maxi_test.Entity.Sale;
import ru.petrov.check_uploader_maxi_test.Repository.SaleRepository;
import ru.petrov.check_uploader_maxi_test.Service.ProductService;
import ru.petrov.check_uploader_maxi_test.Service.SaleService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    SaleService saleService;

    @GetMapping("/")
    public String indexPage(Model model){
        List<Sale> sales = saleService.getAll();
        model.addAttribute("products", productService.getBySale(sales.get(0)));
        return "index";
    }
}
