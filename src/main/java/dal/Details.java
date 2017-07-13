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

public class Details {

    private Integer id;
    private String link;
    private Double price;
    private String img;
    private String sku;
    private Integer colorId;
    private Integer itemId;
    private Integer positionId;
    private Integer styleId;

    public Details() {
    }

    public Details(String link, Double price, String img, String sku, Integer colorId, Integer itemId, Integer positionId, Integer styleId) {
        this.link = link;
        this.price = price;
        this.img = img;
        this.sku = sku;
        this.colorId = colorId;
        this.itemId = itemId;
        this.positionId = positionId;
        this.styleId = styleId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }
    
    

    @Override
    public String toString() {
        return "dal.Details[ id=" + id + " ]";
    }
    
}
