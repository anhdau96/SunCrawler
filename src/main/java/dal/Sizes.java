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

public class Sizes {

    private Integer id;
    private String sizeName;
    public Sizes() {
    }

    public Sizes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Sizes(String sizeName) {
        this.sizeName = sizeName;
    }

    public Sizes(Integer id, String sizeName) {
        this.id = id;
        this.sizeName = sizeName;
    }

    @Override
    public String toString() {
        return "dal.Sizes[ id=" + id + " ]";
    }
    
}
