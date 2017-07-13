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

public class Colors {

    private Integer id;
    private String name;
    private String imglink;

    public Colors() {
    }

    public Colors(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public Colors(String name, String imglink) {
        this.name = name;
        this.imglink = imglink;
    }

    public Colors(Integer id, String name, String imglink) {
        this.id = id;
        this.name = name;
        this.imglink = imglink;
    }

    @Override
    public String toString() {
        return "dal.Colors[ id=" + id + " ]";
    }
    
}
