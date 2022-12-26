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
public interface PublisherDAO {

    String PUBLISHER_INSERT_QUERY 
            = "INSERT INTO publisher(name, address, phone) "
            + "VALUES(?, ?, ?)";
    
    String PUBLISHER_GET_ALL_QUERY 
            = "SELECT * FROM publisher";
    
    String PUBLISHER_UPDATE_QUERY 
            = "UPDATE publisher "
            + "SET name = ?, address = ?, phone = ? "
            + "WHERE publisherID = ?";
    
    String PUBLISHER_DELETE_QUERY 
            = "DELETE FROM publisher "
            + "WHERE publisherID = ?";
    
    String PUBLISHER_CHECK_NAME_QUERY 
            = "SELECT name "
            + "FROM publisher "
            + "WHERE name = ?";

    public void createPublisher(String name, String address, String phone);
    public void loadPublisher(JTable jTable);
    public void updatePublisher(int id, String name, String address, String phone);
    public void deletePublisher(int id);
    public boolean publisherNameExists(String name);
}
