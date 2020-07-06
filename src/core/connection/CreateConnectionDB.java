/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.core.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lequangbkhn
 */
public class CreateConnectionDB {

    private ResultSet rs;
    private Connection con;
    private PreparedStatement ps;

    public CreateConnectionDB() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/it3100", "root", "20109609");
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public ResultSet select(String query) {
        if (con != null) {
            try {
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }
        return rs;
    }

    public boolean insert(String query) {
        boolean bool = false;
        if (con != null) {
            try {
                ps = con.prepareStatement(query);
                bool = ps.execute();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }
        return bool;
    }

    public int update(String query) {
        int res = 0;
        if (con != null) {
            try {
                ps = con.prepareCall(query);
                res = ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public void closeConnectDB() {
        try {
            ps.close();
            con.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
