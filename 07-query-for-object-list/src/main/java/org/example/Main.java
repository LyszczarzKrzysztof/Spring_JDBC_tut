package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        org.example.ProductsRepository productsRepository = context.getBean("productsRepository", org.example.ProductsRepository.class);

//        int numberOfProducts = productsRepository.getNumberOfProducts();
//        System.out.println("Number of products: " + numberOfProducts);
//        ;
//        int numberOfProductsWithPriceGreaterThan = productsRepository.getNumberOfProductsWithPriceGreaterThan(70.0);
//        System.out.println("Number of Products: " + numberOfProductsWithPriceGreaterThan);
//
//        Map<String, Object> productsByProductCode = productsRepository.getProductsByProductCode("S10_1678");
//        System.out.println("Products by specific code: " + productsByProductCode);
//
//        List<Map<String, Object>> productsWithPriceGreaterThan = productsRepository.getProductsWithPriceGreaterThan(70.0);
//        for (Map<String, Object> product : productsWithPriceGreaterThan) {
//            System.out.println("Product: " + product);

        List<Double> productPrices = productsRepository.getProductPrices();
        System.out.println(productPrices);
//        wypisanie sredniej za pomoca streama
        System.out.println(productPrices
                .stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .getAsDouble());
    }
}


