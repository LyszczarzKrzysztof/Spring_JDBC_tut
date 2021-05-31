package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        OfficesRepository officesRepository = context.getBean("officesRepository",
                OfficesRepository.class);

        List<Office> allOffices = officesRepository.getAllOffices();

        for (Office o: allOffices) {
            System.out.println(o);
        }

    }
}


