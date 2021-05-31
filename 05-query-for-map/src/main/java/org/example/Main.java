package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        org.example.ProductsRepository productsRepository = context.getBean("productsRepository", org.example.ProductsRepository.class);
        int numberOfProducts = productsRepository.getNumberOfProducts();
        System.out.println("Number of products: "+ numberOfProducts);;
        int numberOfProductsWithPriceGreaterThan = productsRepository.getNumberOfProductsWithPriceGreaterThan(70.0);
        System.out.println("Number of Products: "+numberOfProductsWithPriceGreaterThan);

        Map<String, Object> productsByProductCode = productsRepository.getProductsByProductCode("S10_1678");
        System.out.println("Products by specific code: "+productsByProductCode);
    }

}
