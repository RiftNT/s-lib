/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slib.dao;

import com.slib.db.DBConnection;
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
public class MemberDAOImpl implements MemberDAO {
    
    private static final String LOGGER_NAME = MemberDAOImpl.class.getName();
    
    public void createMember(String name, String address, String phone) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(MEMBER_INSERT_QUERY)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone);
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }

    public void loadMember(JTable jTable) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(MEMBER_GET_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("memberID"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone")
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }
    
    public void updateMember(int id, String name, String address, String phone) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(MEMBER_UPDATE_QUERY)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }
    
    public void deleteMember(int id) {
        try ( PreparedStatement stmt = DBConnection.makePreparedStatement(MEMBER_DELETE_QUERY)) {
            stmt.setBoolean(1, true);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LOGGER_NAME);
        }
    }
}
