/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slib.dao;

import com.slib.db.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class CategoryDAOImpl implements CategoryDAO {
    
    private static final String LOGGER_NAME = CategoryDAOImpl.class.getName();

    public void createCategory(String name) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CATEGORY_INSERT_QUERY)) {
            stmt.setString(1, name);
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }

    public void loadCategory(JTable jTable) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CATEGORY_GET_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("categoryID"),
                    rs.getString("name")
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }

    public void updateCategory(int id, String name) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CATEGORY_UPDATE_QUERY)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }

    public void deleteCategory(int id) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CATEGORY_DELETE_QUERY)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }

}
