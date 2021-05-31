package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        ProductsRepository productsRepository = context.getBean("productsRepository", ProductsRepository.class);

        List<Map<String, Object>> productsWithProductLine= productsRepository
                .getProductsWithProductLine(Collections.singletonList("Motorcycles"));
        for (Map<String, Object> product : productsWithProductLine) {
            System.out.println(product);
        }

        System.out.println(productsWithProductLine.size());


    }
}


