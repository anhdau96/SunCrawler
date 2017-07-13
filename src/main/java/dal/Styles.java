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

public class Styles {

    private Integer id;
    private String styleName;
    
    public Styles() {
    }

    public Styles(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public Styles(String styleName) {
        this.styleName = styleName;
    }

    public Styles(Integer id, String styleName) {
        this.id = id;
        this.styleName = styleName;
    }


    @Override
    public String toString() {
        return "dal.Styles[ id=" + id + " ]";
    }
    
}
