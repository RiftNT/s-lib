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
public class MemberUtils {
    
    private static final String LOGGER_NAME = MemberUtils.class.getName();
    
    private static final String CHECK_MEMBER_ID_QUERY 
            = "SELECT memberID "
            + "FROM member "
            + "WHERE memberID = ?";
    
    private static final String CHECK_MEMBER_DELETED_QUERY
            = "SELECT memberID "
            + "FROM member "
            + "WHERE memberID = ? AND isDeleted IS TRUE";
    
    public static boolean memberIdExists(int memberID) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_MEMBER_ID_QUERY)) {
            stmt.setInt(1, memberID);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        return false;
    }
    
    public static boolean memberIsDeleted(int memberID) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_MEMBER_DELETED_QUERY)) {
            stmt.setInt(1, memberID);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        return false;
    }
}
