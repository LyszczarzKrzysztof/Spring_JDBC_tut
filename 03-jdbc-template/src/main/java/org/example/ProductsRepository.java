package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ProductsRepository {
    private JdbcTemplate jdbcTemplate;

//    opcja 1

//    @Autowired
//    public ProductsRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

//    opcja 2

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }



    public int getNumberOfProducts() {
       return jdbcTemplate.queryForObject("select count(*) from products", Integer.class);
    }
}
