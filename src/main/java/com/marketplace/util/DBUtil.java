package com.marketplace.util;

import com.marketplace.dao.ProductDAO;
import com.marketplace.dao.SearchProductsDAO;
import com.marketplace.dao.UserDAO;
import com.marketplace.dao.impl.ProductDAOImpl;
import com.marketplace.dao.impl.SearchProductsDAOMockImpl;
import com.marketplace.dao.impl.UserDAOImpl;
import com.marketplace.entity.Product;
import com.marketplace.entity.User;

import java.sql.SQLException;
import java.util.List;

public class DBUtil {

    private static UserDAO userDAO = new UserDAOImpl();
    private static ProductDAO productDAO = new ProductDAOImpl();
    private static SearchProductsDAO searchProductsDAO = new SearchProductsDAOMockImpl();

    public static List<Product> getProducts() {
        try {
            return searchProductsDAO.getProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addProductToOrder(Product product) {
    }

    public static void addProduct(Product product) {
        try {
            productDAO.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(Product product) {
        try {
            productDAO.deleteProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Product getProduct(int id) {
        try {
            return productDAO.getProduct(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User getUser(String login, String password) throws SQLException {
        return userDAO.getByLoginPassword(login, password);
    }

    public static void addUser(User user) throws Exception {
        try {
            userDAO.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(User user) throws Exception {
        try {
            userDAO.delete(user);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) throws Exception {
        userDAO.update(user);
    }
}
