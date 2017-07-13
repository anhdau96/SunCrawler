/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.ConnectDB;
import dal.Colors;
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
public class ColorsController {

    public int create(Colors colors) {
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("INSERT INTO `colors` (`name`, `imglink`) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, colors.getName());
            pst.setString(2, colors.getImglink());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            generatedKeys.next();
            int aInt = generatedKeys.getInt(1);
            connectDB.closeConnect();
            return aInt;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ColorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Colors findByName(String name) {
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("SELECT * FROM `colors` WHERE name = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            Colors c = null;
            if (rs.next()) {
                c = new Colors(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            connectDB.closeConnect();
            return c;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ColorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int firstOrCreate(String name, String link) {
        Colors findByName = findByName(name);
        if (findByName == null) {
            Colors c = new Colors(name, link);
            int create = create(c);
            return create;
        } else {
            return findByName.getId();
        }
    }

}
