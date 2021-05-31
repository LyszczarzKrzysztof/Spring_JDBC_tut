package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class ProductsRepository {
    private JdbcTemplate jdbcTemplate;

//    opcja 1

//    @Autowired
//    public org.example.ProductsRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    //    opcja 2
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getNumberOfProducts() {
        return jdbcTemplate.queryForObject("select count(*) from products", Integer.class);
    }

    public int getNumberOfProductsWithPriceGreaterThan(double priceLimit) {
        String sql = "select count(*) from products where buyPrice > ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, priceLimit);
    }
/*
    wont work
    public int getNumberOfProductsWithLine(List<String> productLines) {
        String sql = "select count(*) from products where productLine in (?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, productLines);
    }
*/

    public Map<String, Object> getProductsByProductCode(String productCode) {
        String s = "select productCode,productName,buyPrice from products where productCode = ?";
        return jdbcTemplate.queryForMap(s, productCode);
    }

}
