package com.project.showCase.jdbc;

import com.project.showCase.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLConnection {

    private static PostgreSQLConnection connection;
    private static Connection con;

    public PostgreSQLConnection() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "bloodDonation");
            System.out.println("Connected to PostgreSQLConnection database!");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Connection to PostgreSQL failed!");
        }
    }

    public static PostgreSQLConnection getInstance() {
        if (connection == null) {
            connection = new PostgreSQLConnection();
        }
        return connection;
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        if (con == null) {
            PostgreSQLConnection.getInstance();
        }
        Statement statement = con.createStatement();
        return statement.executeQuery(query);
    }

    public static Integer executeQueryPreparedStm(String query) throws SQLException {
        if (con == null) {
            PostgreSQLConnection.getInstance();
        }
        PreparedStatement preparedStatement = con.prepareStatement(query);

        return preparedStatement.executeUpdate();
    }

    public static List<Product> getProducts() throws SQLException {
        String query = "select * from product";
        ResultSet resultSet = executeQuery(query);
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getDouble("price"));
            product.setSeller(resultSet.getString("seller"));
            products.add(product);
        }
        return products;
    }

    public static Product getProductById(Integer id) throws SQLException {
        String query = "select * from product where id = "+ id + "";
        ResultSet resultSet = executeQuery(query);
        Product product = new Product();
        if (resultSet.next()) {
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getDouble("price"));
            product.setSeller(resultSet.getString("seller"));
        }
        return product;
    }

    public static String addProduct(Product product) throws SQLException {
        String query = "insert into product(name, description, price, seller) values('" + product.getName() + "', '"
                + product.getDescription() + "', " + product.getPrice() + ", '" + product.getSeller() + "')";
        executeQueryPreparedStm(query);
        return "Product added!";
    }

    public static String updateProduct(Product product) throws SQLException {
        String query = "update product set name = '" + product.getName() + "' where id = '" + product.getId() + "'";
        executeQueryPreparedStm(query);
        return "Product updated!";
    }

    public static String deleteProduct(Integer id) throws SQLException {
        String query = "delete from product where id = '" + id + "'";
        executeQueryPreparedStm(query);
        return "Product deleted!";
    }
}





