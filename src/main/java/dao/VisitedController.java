/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.ConnectDB;
import dal.Visited;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SaiBack
 */
public class VisitedController {

    public int create(Visited visited) {
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("INSERT INTO `visited` (`link`) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, visited.getLink());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            generatedKeys.next();
            int aInt = generatedKeys.getInt(1);
            connectDB.closeConnect();
            return aInt;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VisitedController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Visited findLink(String link) {
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("SELECT * FROM `visited` WHERE link = ?");
            pst.setString(1, link);
            ResultSet rs = pst.executeQuery();
            Visited v = null;
            if (rs.next()) {
                v = new Visited(rs.getInt(1), rs.getString(2));
            }
            connectDB.closeConnect();
            return v;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ColorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
