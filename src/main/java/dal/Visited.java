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

public class Visited {

    private Integer id;
    private String link;

    public Visited() {
    }

    public Visited(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Visited(Integer id, String link) {
        this.id = id;
        this.link = link;
    }

    @Override
    public String toString() {
        return "dal.Visited[ id=" + id + " ]";
    }
    
}
