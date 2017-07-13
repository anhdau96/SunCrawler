/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.ConnectDB;
import dal.Sizes;
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
public class SizesController {
    public int create(Sizes sizes){
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("INSERT INTO `sizes` (`sizeName`) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, sizes.getSizeName());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            generatedKeys.next();
            int aInt = generatedKeys.getInt(1);
            connectDB.closeConnect();
            return aInt;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SizesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public Sizes findByName(String name) {
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("SELECT * FROM `sizes` WHERE sizeName = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            Sizes s = null;
            if (rs.next()) {
                s = new Sizes(rs.getInt(1), rs.getString(2));
            }
            connectDB.closeConnect();
            return s;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SizesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int firstOrCreate(String name) {
        Sizes findByName = findByName(name);
        if (findByName == null) {
            Sizes c = new Sizes(name);
            int create = create(c);
            return create;
        } else {
            return findByName.getId();
        }
    }
}
