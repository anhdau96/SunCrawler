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

public class DetailSize {

    private Integer id;
    private Integer idSize;
    private Integer idDetail;

    public DetailSize() {
    }

    public DetailSize(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DetailSize(Integer idSize, Integer idDetail) {
        this.idSize = idSize;
        this.idDetail = idDetail;
    }

    public Integer getIdSize() {
        return idSize;
    }

    public void setIdSize(Integer idSize) {
        this.idSize = idSize;
    }

    public Integer getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Integer idDetail) {
        this.idDetail = idDetail;
    }

   

    @Override
    public String toString() {
        return "dal.DetailSize[ id=" + id + " ]";
    }
    
}
