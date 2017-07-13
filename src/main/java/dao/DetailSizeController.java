/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.ConnectDB;
import dal.DetailSize;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SaiBack
 */
public class DetailSizeController {
    public void create(DetailSize detailSize){
        try {
            ConnectDB connectDB= new ConnectDB();
            Connection connect = connectDB.getConnect();
            PreparedStatement pst = connect.prepareStatement("INSERT INTO `detail_size` (`idDetail`, `idSize`) VALUES (?, ?)");
            pst.setInt(1, detailSize.getIdDetail());
            pst.setInt(2, detailSize.getIdSize());
            pst.executeUpdate();
            connectDB.closeConnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DetailSizeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
