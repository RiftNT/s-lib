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
public class BookUtils {
    
    private static final String LOGGER_NAME = BookUtils.class.getName();
    
    private static final String CHECK_BOOK_ISBN_QUERY
            = "SELECT isbn "
            + "FROM book "
            + "WHERE isbn = ?";
    
    private static final String SET_BOOK_STATUS_QUERY
            = "UPDATE book "
            + "SET status = ? "
            + "WHERE isbn = ?";
    
    private static final String CHECK_BOOK_STATUS_QUERY
            = "SELECT status "
            + "FROM book "
            + "WHERE isbn = ?";
    
    private static final String CHECK_BOOK_DELETED_QUERY
            = "SELECT isDeleted "
            + "FROM book "
            + "WHERE isbn = ?";
    
    private static final String RECOVER_BOOK_QUERY
            = "UPDATE book "
            + "SET isDeleted = ? "
            + "WHERE isbn = ?";
    
    private static final String CHECK_BOOK_CATEGORY
            = "SELECT categoryID "
            + "FROM book "
            + "WHERE categoryID = ?";
    
    private static final String CHECK_BOOK_PUBLISHER
            = "SELECT publisherID "
            + "FROM book "
            + "WHERE publisherID = ?";
    
    public static boolean bookIsbnExists(String isbn) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_BOOK_ISBN_QUERY)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        return false;
    }
    
    public static void bookSetStatus(String isbn, String status) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(SET_BOOK_STATUS_QUERY)) {
            stmt.setString(1, status);
            stmt.setString(2, isbn);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }
    
    public static boolean bookIsAvailable(String isbn) {
        boolean isAvailable = false;
        
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_BOOK_STATUS_QUERY)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String status = rs.getString("status");
                isAvailable = status.equals("Available");
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        
        return isAvailable;
    }
    
    public static boolean bookIsDeleted(String isbn) {        
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_BOOK_DELETED_QUERY)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("isDeleted") == 1;
            } 
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        
        return false;
    }
    
    public static void bookRecover(String isbn) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(RECOVER_BOOK_QUERY)) {
            stmt.setInt(1, 0);
            stmt.setString(2, isbn);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }
    
    public static boolean bookCheckCategory(int id) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_BOOK_CATEGORY)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        
        return false;
    }
    
    public static boolean bookCheckPublisher(int id) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_BOOK_PUBLISHER)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        
        return false;
    }
}
