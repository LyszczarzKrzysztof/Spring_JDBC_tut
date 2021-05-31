package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OfficesRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Office getOfficeByOfficeCode(String officeCode) {
        return jdbcTemplate.queryForObject("select * from offices where officeCode = ?", new OfficeRowMapper(), officeCode);
    }

//    aby pobrac liste obiektow to korzystamy z samego query, w srodku instancja rowmappera (zdefiniowany obiekt w rowmapper
//    w klasie wewnetrznej statycznej - pewnie mozna inaczej instancje wykonac, i podajemy sql i instancje rowmapper w query

    public List<Office> getAllOffices(){
        String sql = "select * from offices";
        return jdbcTemplate.query(sql, new OfficeRowMapper());
    }

    private static class OfficeRowMapper implements RowMapper<Office> {
        @Override
        public Office mapRow(ResultSet resultSet, int i) throws SQLException {
            Office office = new Office();
            office.setOfficeCode(resultSet.getString("officeCode"));
            office.setAddressLine1(resultSet.getString("addressLine1"));
            office.setAddressLine2(resultSet.getString("addressLine2"));
            office.setCountry(resultSet.getString("country"));
            office.setPhone(resultSet.getString("phone"));
            office.setState(resultSet.getString("state"));
            office.setPostalCode(resultSet.getString("postalCode"));
            office.setTerritory(resultSet.getString("territory"));
            office.setCity(resultSet.getString("city"));
            return office;
        }
    }
}
