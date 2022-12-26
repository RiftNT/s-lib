/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.slib.dao;

import javax.swing.JTable;

/**
 *
 * @author User
 */
public interface BookDAO {
    
    String BOOK_INSERT_QUERY
            = "INSERT INTO book(isbn, title, categoryID, publisherID, pages, edition, status) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?)";

    String BOOK_GET_ALL_QUERY
            = "SELECT b.*, c.name AS catName, p.name AS pubName "
            + "FROM book AS b "
            + "JOIN category AS c USING (categoryID) "
            + "JOIN publisher AS p USING (publisherID) "
            + "WHERE b.isDeleted = 0";

    String BOOK_UPDATE_QUERY
            = "UPDATE book "
            + "SET title = ?, categoryID = ?, publisherID = ?, pages = ?, edition = ?, status = ? "
            + "WHERE isbn = ?";

    String BOOK_DELETE_QUERY
            = "UPDATE book "
            + "SET isDeleted = ? "
            + "WHERE isbn = ?";
    
    public void createBook(String isbn, String title, String catName, String pubName, int pages, int edition, String status);
    public void loadBook(JTable jTable);
    public void updateBook(String isbn, String title, String catName, String pubName, int pages, int edition, String status);
    public void deleteBook(String isbn);
}
