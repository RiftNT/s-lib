/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slib.dao;

import com.slib.db.DBConnection;
import com.slib.utils.CategoryUtils;
import com.slib.utils.PublisherUtils;
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
public class BookDAOImpl implements BookDAO {

    private static final String LOGGER_NAME = BookDAOImpl.class.getName();

    public void createBook(String isbn, String title, String catName, String pubName, int pages, int edition, String status) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(BOOK_INSERT_QUERY)) {
            
            int categoryID = CategoryUtils.getCategoryID(catName);
            int publisherID = PublisherUtils.getPublisherID(pubName);

            stmt.setString(1, isbn);
            stmt.setString(2, title);
            stmt.setInt(3, categoryID);
            stmt.setInt(4, publisherID);
            stmt.setInt(5, pages);
            stmt.setInt(6, edition);
            stmt.setString(7, status);
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }

    public void loadBook(JTable jTable) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(BOOK_GET_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("isbn"),
                    rs.getString("title"),
                    rs.getString("catName"),
                    rs.getString("pubName"),
                    rs.getInt("pages"),
                    rs.getInt("edition"),
                    rs.getString("status")
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }

    public void updateBook(String isbn, String title, String catName, String pubName, int pages, int edition, String status) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(BOOK_UPDATE_QUERY)) {

            int categoryID = CategoryUtils.getCategoryID(catName);
            int publisherID = PublisherUtils.getPublisherID(pubName);

            stmt.setString(1, title);
            stmt.setInt(2, categoryID);
            stmt.setInt(3, publisherID);
            stmt.setInt(4, pages);
            stmt.setInt(5, edition);
            stmt.setString(6, status);
            stmt.setString(7, isbn);

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }
    
    public void deleteBook(String isbn) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(BOOK_DELETE_QUERY)) {
            stmt.setInt(1, 1);
            stmt.setString(2, isbn);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }
}
