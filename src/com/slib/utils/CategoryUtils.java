/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slib.utils;

import com.slib.db.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class CategoryUtils {
    
    private static final String LOGGER_NAME = CategoryUtils.class.getName();
    
    private static final String GET_CATEGORY_ID_QUERY
            = "SELECT categoryID "
            + "FROM category "
            + "WHERE name = ?";
    
    private static final String CHECK_CATEGORY_NAME_QUERY 
            = "SELECT name "
            + "FROM category "
            + "WHERE name = ?";
    
    public static int getCategoryID(String name) {
        int id = -1;

        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(GET_CATEGORY_ID_QUERY)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("categoryID");
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }

        return id;
    }
    
    public static boolean categoryNameExists(String name) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_CATEGORY_NAME_QUERY)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        return false;
    }
}
