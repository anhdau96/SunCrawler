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

public class Items {

    private Integer id;
    private String title;
    private String link;
    private String cate;
    private String subcate;
    private String description;
    private String keyword;

    public Items() {
    }

    public Items(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getSubcate() {
        return subcate;
    }

    public void setSubcate(String subcate) {
        this.subcate = subcate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Items(String title, String link, String cate, String subcate, String description, String keyword) {
        this.title = title;
        this.link = link;
        this.cate = cate;
        this.subcate = subcate;
        this.description = description;
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "dal.Items[ id=" + id + " ]";
    }
    
}
