/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slib.dao;

import com.slib.db.DBConnection;
import com.slib.utils.BookUtils;
import com.slib.utils.IssueReturnUtils;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Calendar;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class IssueReturnDAOImpl implements IssueReturnDAO {

    private static final String LOGGER_NAME = IssueReturnDAOImpl.class.getName();
    private static final BigDecimal DAILY_FEE = new BigDecimal(10.00);

    public void createIssue(int memberID, String isbn) {
        // Generate issue date to current date and
        // due date to current date + 7 days
        LocalDate issuedate = LocalDate.now();
        LocalDate duedate = issuedate.plusDays(7);

        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(ISSUE_INSERT_QUERY)) {
            stmt.setInt(1, memberID);
            stmt.setString(2, isbn);
            stmt.setDate(3, Date.valueOf(issuedate));
            stmt.setDate(4, Date.valueOf(duedate));
            stmt.execute();
            
            BookUtils.bookSetStatus(isbn, "Unavailable");
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }

    public void loadIssues(JTable jTable) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(ISSUE_GET_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("issueID"),
                    rs.getInt("memberID"),
                    rs.getString("memName"),
                    rs.getString("isbn"),
                    rs.getString("bookTitle"),
                    rs.getDate("issuedate"),
                    rs.getDate("duedate")
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }

    public void returnBook(int memberID, String isbn) {
        // If the book was returned after the due date,
        // increment fee to 10 each day the book is returned late
        LocalDate returndate = LocalDate.now();
        LocalDate duedate = IssueReturnUtils.getDueDate(memberID, isbn);
        
        BigDecimal fee = new BigDecimal(0);
        
        if (returndate.isAfter(duedate)) {
            long daysLate = DAYS.between(duedate, returndate);
            fee = fee.add(DAILY_FEE.multiply(new BigDecimal(daysLate)));
        }

        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(RETURN_BOOK_QUERY)) {
            stmt.setDate(1, Date.valueOf(returndate));
            stmt.setBigDecimal(2, fee);
            stmt.setInt(3, memberID);
            stmt.setString(4, isbn);
            stmt.executeUpdate();
            BookUtils.bookSetStatus(isbn, "Available");
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }
    
    public void loadReturns(JTable jTable) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(RETURN_GET_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("issueID"),
                    rs.getInt("memberID"),
                    rs.getString("memName"),
                    rs.getString("isbn"),
                    rs.getString("bookTitle"),
                    rs.getDate("issuedate"),
                    rs.getDate("duedate"),
                    rs.getDate("returndate"),
                    rs.getBigDecimal("fee")
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }
}
