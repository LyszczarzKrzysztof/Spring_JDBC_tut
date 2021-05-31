package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        ProductsRepository productsRepository = context.getBean("productsRepository", ProductsRepository.class);
        List<Map<String, Object>> productsWithPriceRange = productsRepository.getProductsWithPriceRange(50.0, 60.0);
        for (Map<String, Object> product : productsWithPriceRange) {
            System.out.println(product);
        }

        System.out.println(productsWithPriceRange.size());

    }
}


