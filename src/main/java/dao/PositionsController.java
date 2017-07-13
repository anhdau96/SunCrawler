/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.ConnectDB;
import dal.Positions;
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
public class PositionsController {

    public int create(Positions positions) {
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("INSERT INTO `positions` (`position`) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, positions.getPosition());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            generatedKeys.next();
            int aInt = generatedKeys.getInt(1);
            connectDB.closeConnect();
            return aInt;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PositionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Positions findByName(String name) {
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("SELECT * FROM `positions` WHERE position = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            Positions p = null;
            if (rs.next()) {
                p = new Positions(rs.getInt(1), rs.getString(2));
            }
            connectDB.closeConnect();
            return p;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PositionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int firstOrCreate(String name) {
        Positions findByName = findByName(name);
        if (findByName == null) {
            Positions p = new Positions(name);
            int create = create(p);
            return create;
        } else {
            return findByName.getId();
        }
    }
}
