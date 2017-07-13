/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import connect.ConnectDB;
import dal.Details;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SaiBack
 */
public class DetailsController {

    public int create(Details details) {
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("INSERT INTO `details` (`link`, `itemId`, `styleId`, `colorId`, `price`, `img`, `positionId`, `sku`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, details.getLink());
            pst.setInt(2, details.getItemId());
            pst.setInt(3, details.getStyleId());
            if (details.getColorId() != null) {
                pst.setInt(4, details.getColorId());
            } else {
                pst.setNull(4, Types.INTEGER);
            }
            pst.setDouble(5, details.getPrice());
            pst.setString(6, details.getImg());
            pst.setInt(7, details.getPositionId());
            pst.setString(8, details.getSku());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            generatedKeys.next();
            int aInt = generatedKeys.getInt(1);
            connectDB.closeConnect();
            return aInt;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
