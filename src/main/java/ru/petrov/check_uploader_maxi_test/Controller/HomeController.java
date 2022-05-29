package ru.petrov.check_uploader_maxi_test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.petrov.check_uploader_maxi_test.Entity.MyCalendar;
import ru.petrov.check_uploader_maxi_test.Entity.Product;
import ru.petrov.check_uploader_maxi_test.Entity.Sale;
import ru.petrov.check_uploader_maxi_test.Service.ProductService;
import ru.petrov.check_uploader_maxi_test.Service.SaleService;

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
        model.addAttribute("myCalendar", new MyCalendar());
        return "index";
    }

    @PostMapping("/")
    public String statPage(
            @RequestParam(value = "cardNumber", required = false) String cardNumber,
            @ModelAttribute("myCalendar") MyCalendar calendar,
            Model model) {
        String error = null;
        if (cardNumber != null) {
            Long tmp = null;
            try {
                tmp = Long.parseLong(cardNumber);
            } catch (NumberFormatException exc) {
                error = String.format("Не верно введен номер карты: %s", cardNumber);
            }
            if (tmp != null) {
                List<Sale> sale = saleService.findByCardNumber(tmp);
                if (sale != null) {
                    model.addAttribute("sale", sale);
                    List<Product> productList = productService.getTop3(tmp);
                    model.addAttribute("top3", productList);
                }
            }
        }
        if (calendar != null) {
            double sum = productService.getSumOfTheDay(calendar.getCalendar());

            model.addAttribute("money", sum);
        }
        model.addAttribute("error", error);
        return "index";
    }




}
