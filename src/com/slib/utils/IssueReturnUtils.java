/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slib.utils;

import com.slib.db.DBConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class IssueReturnUtils {

    private static final String LOGGER_NAME = IssueReturnUtils.class.getName();

    private static final String GET_DUE_DATE_QUERY
            = "SELECT duedate "
            + "FROM issue "
            + "WHERE memberID = ? AND isbn = ?";
    
    private static final String CHECK_ISSUE_EXISTS_QUERY
            = "SELECT issueID "
            + "FROM issue "
            + "WHERE memberID = ? AND isbn = ?";
    
    private static final String CHECK_BOOK_RETURNED_QUERY
            = "SELECT returndate "
            + "FROM issue "
            + "WHERE memberID = ? AND isbn = ?";
    
    private static final String CHECK_BOOK_CURR_ISSUED_QUERY
            = "SELECT returndate "
            + "FROM issue "
            + "WHERE isbn = ? AND returndate IS NULL";

    public static LocalDate getDueDate(int memberID, String isbn) {
        Date duedate = null;
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(GET_DUE_DATE_QUERY)) {
            stmt.setInt(1, memberID);
            stmt.setString(2, isbn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                duedate = rs.getDate("duedate");
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }

        return duedate.toLocalDate();
    }
    
    public static boolean issueExists(int memberID, String isbn) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_ISSUE_EXISTS_QUERY)) {
            stmt.setInt(1, memberID);
            stmt.setString(2, isbn);
            ResultSet rs = stmt.executeQuery();
            
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        return false;
    }
    
    public static boolean isReturnedBook(int memberID, String isbn) {
        Date returndate;
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_BOOK_RETURNED_QUERY)) {
            stmt.setInt(1, memberID);
            stmt.setString(2, isbn);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                returndate = rs.getDate("returndate");
                if(rs.wasNull()) {
                    return false;
                }
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        return true;
    }
    
    public static boolean isCurrentlyIssuedBook(String isbn) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_BOOK_CURR_ISSUED_QUERY)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        return false;
    }
    
}
