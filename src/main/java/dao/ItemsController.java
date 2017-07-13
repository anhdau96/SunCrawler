/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import connect.ConnectDB;
import dal.Items;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SaiBack
 */
public class ItemsController {
    public int create(Items items){
        try {
            ConnectDB connectDB= new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("INSERT INTO `items` (`title`, `link`, `cate`, `subcate`, `description`, `keyword`) VALUES ( ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, items.getTitle());
            pst.setString(2, items.getLink());
            pst.setString(3, items.getCate());
            pst.setString(4, items.getSubcate());
            pst.setString(5, items.getDescription());
            pst.setString(6, items.getKeyword());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            generatedKeys.next();
            int aInt = generatedKeys.getInt(1);
            connectDB.closeConnect();
            return aInt;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
