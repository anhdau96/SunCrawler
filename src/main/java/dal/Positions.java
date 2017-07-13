/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;


/**
 *
 * @author SaiBack
 */

public class Positions {

    private Integer id;
    private String position;

    public Positions() {
    }

    public Positions(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Positions(String position) {
        this.position = position;
    }

    public Positions(Integer id, String position) {
        this.id = id;
        this.position = position;
    }


    @Override
    public String toString() {
        return "dal.Positions[ id=" + id + " ]";
    }
    
}
