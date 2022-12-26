/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slib.dao;

import javax.swing.JTable;

/**
 *
 * @author User
 */
public interface CategoryDAO {

    String CATEGORY_INSERT_QUERY 
            = "INSERT INTO category(name) "
            + "VALUES(?)";
    
    String CATEGORY_GET_ALL_QUERY 
            = "SELECT * "
            + "FROM category";
    
    String CATEGORY_UPDATE_QUERY 
            = "UPDATE category "
            + "SET name = ? "
            + "WHERE categoryID = ?";
    
    String CATEGORY_DELETE_QUERY 
            = "DELETE FROM category "
            + "WHERE categoryID = ?";
    
    public void createCategory(String name);
    public void loadCategory(JTable jTable);
    public void updateCategory(int id, String name);
    public void deleteCategory(int id);
}
