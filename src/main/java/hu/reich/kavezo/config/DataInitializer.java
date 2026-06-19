package hu.reich.kavezo.config;

import hu.reich.kavezo.model.Product;
import hu.reich.kavezo.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductService productService;

    public DataInitializer(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        // Csak akkor töltse fel, ha még nincs adat
        if (productService.findAll().isEmpty()) {
            System.out.println("Example product uploading...");

            productService.save(new Product("Espresso", "Erős olasz kávé", 890, "Kávé"));
            productService.save(new Product("Cappuccino", "Espresso tejhabbal", 1290, "Kávé"));
            productService.save(new Product("Croissant", "Friss vaj croissant", 690, "Sütemény"));
            productService.save(new Product("Matcha Latte", "Zöld tea latte", 1490, "Ital"));
            productService.save(new Product("Tiramisu", "Klasszikus olasz desszert", 890, "Sütemény"));

            System.out.println("Example product upload finished!");
        }
    }
}