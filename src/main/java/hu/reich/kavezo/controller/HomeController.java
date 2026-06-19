package hu.reich.kavezo.controller;

import hu.reich.kavezo.model.Order;
import hu.reich.kavezo.model.Product;
import hu.reich.kavezo.service.OrderService;
import hu.reich.kavezo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;
    private final OrderService orderService;

    public HomeController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("cafeName", "Kávézó Neve");
        model.addAttribute("slogan", "Ahol minden csésze egy élmény");
        model.addAttribute("products", productService.findAll());
        model.addAttribute("orders", orderService.findAll());
        return "index";
    }

    @PostMapping("/add-order")
    public String addOrder(@RequestParam String customerName,
                           @RequestParam List<Long> productIds) {
        List<Product> selectedProducts = productService.findAll().stream()
                .filter(p -> productIds.contains(p.getId()))
                .toList();

        orderService.saveOrder(customerName, selectedProducts);
        return "redirect:/";
    }
}