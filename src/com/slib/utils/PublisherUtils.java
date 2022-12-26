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
public class PublisherUtils {
    
    private static final String LOGGER_NAME = PublisherUtils.class.getName();

    private static final String GET_PUBLISHER_ID_QUERY
            = "SELECT publisherID "
            + "FROM publisher "
            + "WHERE name = ?";
    
    private static final String CHECK_PUBLISHER_NAME_QUERY 
            = "SELECT name "
            + "FROM publisher "
            + "WHERE name = ?";
    
    private static final String CHECK_PUBLISHER_ID_QUERY
            = "Select publisherID "
            + "FROM publisher "
            + "WHERE publisherID = ?";
    
    public static int getPublisherID(String name) {
        int id = -1;

        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(GET_PUBLISHER_ID_QUERY)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("publisherID");
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }

        return id;
    }
    
    public static boolean publisherNameExists(String name) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_PUBLISHER_NAME_QUERY)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        return false;
    }
    
    public static boolean publisherIdExists(int id) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(CHECK_PUBLISHER_ID_QUERY)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
        return false;
    }
}
