/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.ConnectDB;
import dal.Styles;
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
public class StylesController {

    public int create(Styles styles) {
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("INSERT INTO `styles` (`styleName`) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, styles.getStyleName());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            generatedKeys.next();
            int aInt = generatedKeys.getInt(1);
            connectDB.closeConnect();
            return aInt;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StylesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Styles findByName(String name) {
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("SELECT * FROM `styles` WHERE styleName = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            Styles s = null;
            if (rs.next()) {
                s = new Styles(rs.getInt(1), rs.getString(2));
            }
            connectDB.closeConnect();
            return s;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StylesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int firstOrCreate(String name) {
        Styles findByName = findByName(name);
        if (findByName == null) {
            Styles s = new Styles(name);
            int create = create(s);
            return create;
        } else {
            return findByName.getId();
        }
    }
}
