package com.project.showCase.jdbc;

import com.project.showCase.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcProductRepository {
//Inicijalno go napraviv so interface ama otposle sfativ deka e rano za abstrakcija, zatoa se iskomentirani @Override implements ProductRepositiory {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Create
//    @Override
    public int save(Product product) {
        return jdbcTemplate.update(
                "insert into product(name, description, price, seller) values(?, ?, ?, ?)", product.getName(),
                product.getDescription(), product.getPrice(), product.getSeller());
    }

    //Read
//    @Override
    public Product findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select * from product where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("price"),
                                rs.getString("seller"))
        );
    }

//    @Override
    public List<Product> findAll() {
        return jdbcTemplate
                .query("select * from product", (rs, rowNum) ->
                        new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("price"),
                                rs.getString("seller"))
                );}

    //Update
//    @Override
    public int updateProductName(Product product) {
        return jdbcTemplate.update(
                "update product set name = ? where id = ?", product.getName(), product.getId());
    }

    //Delete
//    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update(
                "delete from product where id = ?", id);
    }

}

//    //Read
//    @Override
//    public Product findById(Integer id) {
//        return (Product) jdbcTemplate.queryForObject(
//                "select * from product where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Product.class));}

//    //Read
//    @Override
//    public Optional<Product> findById(Integer id) {
//        return jdbcTemplate.queryForObject(
//                "select * from product where id = ?",
//                new Object[]{id},
//                (rs, rowNum) ->
//                        Optional.of(new Product(
//                                rs.getInt("id"),
//                                rs.getString("name"),
//                                rs.getString("description"),
//                                rs.getDouble("price"),
//                                rs.getString("seller")))
//        );}