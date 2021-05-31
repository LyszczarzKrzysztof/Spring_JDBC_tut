package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class ProductsRepository {


    private JdbcTemplate jdbcTemplate;

//    do przekazywanie parametrow nazwanych stosujemy:

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    opcja 1

//    @Autowired
//    public org.example.ProductsRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    //    opcja 2
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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

    public List<Map<String, Object>> getProductsWithPriceGreaterThan(double priceLimit) {
        String sql = "select * from products where buyPrice > ?";
        return jdbcTemplate.queryForList(sql, priceLimit);
    }

    public List<Double> getProductPrices() {
        String sql = "select buyPrice from products";
        return jdbcTemplate.queryForList(sql, Double.class);
    }

    public List<Map<String, Object>> getProductsWithPriceRange(double minPrice, double maxPrice) {
        String sql = "select * from products where buyPrice >= :minPrice and buyPrice <= :maxPrice";
//przyklad z dodawaniem wielu wartosci przy pomocy metody addValue
//        SqlParameterSource parameters = new MapSqlParameterSource("minPrice", minPrice)
//                .addValue("maxPrice", maxPrice);

        Map<String, Object> params = new HashMap<>();
        params.put("minPrice", minPrice);
        params.put("maxPrice", maxPrice);

//        SqlParameterSource parameters = new MapSqlParameterSource(params);

//        return namedParameterJdbcTemplate.queryForList(sql, parameters);

//        trzeci sposób podajemy mape parametow bezposrednio do queryForList():

        return namedParameterJdbcTemplate.queryForList(sql, params);
    }

    public List<Map<String, Object>> getProductsWithProductLine(List<String> productLines){
        String sql = "select * from products where productLine in (:productLines)";
        Map<String, List<String>> parameters = Collections.singletonMap("productLines", productLines);
        return namedParameterJdbcTemplate.queryForList(sql, parameters);
    }
}
