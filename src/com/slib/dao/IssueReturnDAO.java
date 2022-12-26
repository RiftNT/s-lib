/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.slib.dao;

import java.sql.Date;
import javax.swing.JTable;

/**
 *
 * @author User
 */
public interface IssueReturnDAO {
    
    String ISSUE_INSERT_QUERY 
            = "INSERT INTO issue(memberID, isbn, issuedate, duedate) "
            + "VALUES(?, ?, ?, ?)";
    
    String ISSUE_GET_ALL_QUERY 
            = "SELECT i.*, m.name AS memName, b.title AS bookTitle "
            + "FROM issue AS i "
            + "JOIN member AS m USING (memberID) "
            + "JOIN book AS b USING (isbn) "
            + "WHERE returndate IS NULL";
    
    String RETURN_BOOK_QUERY 
            = "UPDATE issue "
            + "SET returndate = ?, fee = ? "
            + "WHERE memberID = ? AND isbn = ?";
    
    String RETURN_GET_ALL_QUERY
            = "SELECT i.*, m.name AS memName, b.title AS bookTitle "
            + "FROM issue AS i "
            + "JOIN member AS m USING (memberID) "
            + "JOIN book AS b USING (isbn)";
    
    public void createIssue(int memberID, String isbn);
    public void loadIssues(JTable jTable);
    public void returnBook(int memberID, String isbn);
    public void loadReturns(JTable jTable);
            
}
