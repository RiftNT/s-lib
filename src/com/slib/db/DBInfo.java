/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slib.db;

/**
 *
 * @author User
 */
interface DBInfo {
    
    final static String DB_NAME = "slib";
    final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    final static String DB_USERNAME = "root";
    final static String DB_PASSWORD = "";
}
