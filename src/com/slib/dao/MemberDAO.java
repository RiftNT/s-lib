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
public interface MemberDAO {

    String MEMBER_INSERT_QUERY 
            = "INSERT INTO member(name, address, phone) "
            + "VALUES(?, ?, ?)";
    
    String MEMBER_GET_ALL_QUERY 
            = "SELECT * "
            + "FROM member "
            + "WHERE isDeleted IS FALSE";
    
    String MEMBER_UPDATE_QUERY 
            = "UPDATE member "
            + "SET name = ?, address = ?, phone = ? "
            + "WHERE memberID = ?";
    
    String MEMBER_DELETE_QUERY 
            = "UPDATE member "
            + "SET isDeleted = ? "
            + "WHERE memberID = ?";

    public void createMember(String name, String address, String phone);
    public void loadMember(JTable jTable);
    public void updateMember(int id, String name, String address, String phone);
    public void deleteMember(int id);
}
