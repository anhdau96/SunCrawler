/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sunfrogcrawlver3;

import dal.Data;
import dal.DetailSize;
import dal.Details;
import dal.Items;
import dal.Visited;
import dao.ColorsController;
import dao.DetailSizeController;
import dao.DetailsController;
import dao.ItemsController;
import dao.PositionsController;
import dao.SizesController;
import dao.StylesController;
import dao.VisitedController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 *
 * @author SaiBack
 */
public class Crawler {
    public static void main(String[] args) {
//        Map<String,String> lstLink = new HashMap<>();
//        Data data = new Data();
//        List<String> lstName = data.getLstName();
//        for (int i = 0; i < lstName.size(); i++) {
//            if (i%4==0) {
//                getDataByCate("https://www.sunfrog.com/search/?search="+lstName.get(i)+"&cID=0&productType=0&schTrmFilter=new", "", "");
//                getDataByCate("https://www.sunfrog.com/search/?search="+lstName.get(i)+"&cID=0&productType=0&schTrmFilter=popular", "", "");
//                getDataByCate("https://www.sunfrog.com/search/?search="+lstName.get(i)+"&cID=0&productType=0&schTrmFilter=sales", "", "");
//                getDataByCate("https://www.sunfrog.com/search/?search="+lstName.get(i)+"&cID=0&productType=0&schTrmFilter=relevance", "", "");
//            }
//        }

        getDataItem("https://www.sunfrog.com/TeamLauren/Michigan", "States", "Michigan Love");
    }

    public static void getSubCate(String link, String cateName) {
        try {
//            System.out.println("Get Sub Cate");
            Document catePage = Jsoup.connect(link).get();
            if (catePage.getElementById("pillbox") == null) {
                if (!checkVisited(link)) {
                    getDataByCate(link, cateName);
                    addVisitLink(link);
                }
                return;
            }
            Element ulElement = catePage.getElementById("pillbox").getElementsByTag("ul").get(0);
            Elements lstSubCates = ulElement.getElementsByTag("a");
            for (Element lstSubCate : lstSubCates) {
                if (lstSubCate.attr("title").equals("")) {
                    continue;
                }
                if (!checkVisited(lstSubCate.attr("href"))) {
                    getDataByCate(lstSubCate.attr("href"), cateName, lstSubCate.attr("title"));
                    addVisitLink(lstSubCate.attr("href"));
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void getDataByCate(String link, String cate) {
        MyDriver myDriver = new MyDriver();
        PhantomJSDriver driver = myDriver.getDriver();
        driver.get(link);
//        System.out.println("Connect Link");
        Document subCatePage = Jsoup.parse(driver.getPageSource());
        myDriver.quitDriver();
        Elements lstShirt = subCatePage.getElementsByAttributeValueContaining("id", "shirtDivDisplay");
        for (Element element : lstShirt) {
            if (!checkVisited(element.getElementsByTag("a").get(0).attr("href"))) {
                addVisitLink(element.getElementsByTag("a").get(0).attr("href"));
                getDataItem(element.getElementsByTag("a").get(0).attr("href"), cate, "");
            }
        }
    }

    public static void getDataByCate(String link, String cate, String subCate) {

        MyDriver myDriver = new MyDriver();
        PhantomJSDriver driver = myDriver.getDriver();
        driver.get(link);
//        System.out.println("Connect Link");
        Document subCatePage = Jsoup.parse(driver.getPageSource());
        Elements lstShirt = subCatePage.getElementsByAttributeValueContaining("id", "shirtDivDisplay");
        for (Element element : lstShirt) {
            if (!checkVisited(element.getElementsByTag("a").get(0).attr("href"))) {
                addVisitLink(element.getElementsByTag("a").get(0).attr("href"));
                getDataItem(element.getElementsByTag("a").get(0).attr("href"), cate, subCate);
            }
        }
        int i = 40;
        link = link.replace("https://www.sunfrog.com/search/", "https://www.sunfrog.com/search/paged3.cfm");
//        driver.quit();
        while (i <= 1000) {
            try {
                System.out.println(link + "&offset=" + (i + 1));
                driver.get(link + "&offset=" + (i + 1));
                Document ajaxPage = Jsoup.parse(driver.getPageSource());
//                Document ajaxPage = Jsoup.connect("https://www.sunfrog.com" + link + "&offset=" + (i + 1)).get();
                if (ajaxPage.getElementsByAttributeValueContaining("id", "shirtDivDisplay").isEmpty()) {
                    break;
                }
                lstShirt = ajaxPage.getElementsByAttributeValueContaining("id", "shirtDivDisplay");
                for (Element element : lstShirt) {
                    if (!checkVisited(element.getElementsByTag("a").get(0).attr("href"))) {
                        addVisitLink(element.getElementsByTag("a").get(0).attr("href"));
                        getDataItem(element.getElementsByTag("a").get(0).attr("href"), cate, subCate);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                i += 40;
            }
        }
        driver.quit();
    }

    public static void getDataItem(String linkShirt, String cate, String subCate) {
        try {
            Document shirtPage = Jsoup.connect(linkShirt).get();
            if (!shirtPage.getElementsByClass("frameit").isEmpty()) {
                Elements lstShirt = shirtPage.getElementsByClass("frameit");
                for (Element element : lstShirt) {
                    if (!checkVisited(element.getElementsByTag("a").get(0).attr("href"))) {
                        addVisitLink(element.getElementsByTag("a").get(0).attr("href"));
                        getDataItem("https://www.sunfrog.com" + element.getElementsByTag("a").get(0).attr("href"), cate, subCate);
                    }

                }
            } else {
                Items items = new Items();
                items.setTitle(shirtPage.getElementById("srshow").html().trim());
                try {
                    items.setDescription(shirtPage.getElementsByAttributeValueMatching("style", "margin:11px 0 22px 0;").get(0).html().trim());
                } catch (Exception exception) {
                    items.setDescription("");
                }
                items.setKeyword(shirtPage.getElementsByAttributeValueMatching("name", "Keywords").get(0).attr("content"));
                items.setLink(linkShirt);
                Element breadcrumb = shirtPage.getElementsByClass("titletopwrap").get(0);
                if (breadcrumb.getElementsByTag("li").size()==3) {
                    cate = breadcrumb.getElementsByTag("li").get(1).getElementsByTag("a").get(0).html();
                } else {
                    cate = "Category Other";
                }
                subCate = "keyword";
                items.setCate(cate);
                items.setSubcate(subCate);
                ItemsController controller = new ItemsController();
                int createItem = controller.create(items);
                System.out.println(items.getTitle());
                getByStyle(linkShirt, createItem);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getByStyle(String link, int itemId) {
        try {
            Document detailPage = Jsoup.connect(link).get();
            Element selectStyle = detailPage.getElementById("shirtTypes");
            Elements styles = selectStyle.getElementsByTag("option");
            StylesController styleContr = new StylesController();
            for (Element style : styles) {
                String styleLink = link.substring(0, link.lastIndexOf("/") + 1) + style.attr("value");
                String styleNameAndPrice = style.html();
                Double price = Double.valueOf(styleNameAndPrice.split(" ")[styleNameAndPrice.split(" ").length - 1].replace("$", ""));
                String styleName = styleNameAndPrice.replace(styleNameAndPrice.split(" ")[styleNameAndPrice.split(" ").length - 1], "").trim();
                int styleCreate = styleContr.firstOrCreate(styleName);
                getByColor(styleLink, styleCreate, itemId, price);
            }
        } catch (Exception ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getByColor(String link, int styleId, int itemId, double price) {
        try {
            ColorsController colorContr = new ColorsController();
            PositionsController positionsJpaContr = new PositionsController();
            Document detailPage = Jsoup.connect(link).get();
            Elements colorLabels;
            try {
                colorLabels = detailPage.getElementsByClass("btn-group").get(0).getElementsByTag("label");
            } catch (Exception e) {
                colorLabels = null;
            }
            if (colorLabels != null) {
                String linkGetSize = "";
                for (Element colorLabel : colorLabels) {
                    String name = colorLabel.attr("title");
                    String onClick = colorLabel.attr("onclick");
                    String[] splits = onClick.split("'");
                    for (String split : splits) {
                        if (split.contains("html")) {
//                            System.out.println(link.substring(0, link.lastIndexOf("/") + 1) + split);
                            linkGetSize = link.substring(0, link.lastIndexOf("/") + 1) + split;
                        }
                    }
                    String style = colorLabel.getElementsByTag("div").get(0).attr("style");
                    String[] styleArrs = style.split("'");
                    String linkColor = "";
                    for (String styleArr : styleArrs) {
                        if (styleArr.contains("https")) {
                            linkColor = styleArr;
                        }
                    }
                    int color = colorContr.firstOrCreate(name, linkColor);
                    Details details = new Details();
                    details.setLink(linkGetSize);
                    details.setItemId(itemId);
                    details.setStyleId(styleId);
                    details.setPrice(price);
                    for (Element e : detailPage.getElementsByTag("small")) {
                        if (e.html().contains("SKU")) {
                            details.setSku(e.html().split(" ")[e.html().split(" ").length - 1]);
                        }
                    }
                    int positions;
                    try {
                        positions = positionsJpaContr.firstOrCreate(detailPage.getElementById("frontorback").html());
                    } catch (Exception exception) {
//                        System.out.println("Back");
                        positions = positionsJpaContr.firstOrCreate(detailPage.getElementsByAttributeValueMatching("class", "btn btn-lg btn-default disabled pull-right visible-lg").html());
                    }
                    details.setPositionId(positions);
                    details.setColorId(color);
                    getBySize(linkGetSize, details);
                }
            } else {
                Details details = new Details();
                details.setLink(link);
                details.setItemId(itemId);
                details.setStyleId(styleId);
                details.setPrice(price);
                for (Element e : detailPage.getElementsByTag("small")) {
                    if (e.html().contains("SKU")) {
                        details.setSku(e.html().split(" ")[e.html().split(" ").length - 1]);
                    }
                }
                int positions;
                try {
                    positions = positionsJpaContr.firstOrCreate(detailPage.getElementById("frontorback").html());
                } catch (Exception exception) {
                   // System.out.println("Back");
                    positions = positionsJpaContr.firstOrCreate(detailPage.getElementsByAttributeValueMatching("class", "btn btn-lg btn-default disabled pull-right visible-lg").html());
                }
                details.setPositionId(positions);
                
                getBySize(link, details);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getBySize(String link, Details details) {
        try {
            SizesController sizesContr = new SizesController();
            DetailSizeController detailSizeJpaContr = new DetailSizeController();
            DetailsController detailsJpaContr = new DetailsController();
            Document detailPage = Jsoup.connect(link).get();
            details.setImg(detailPage.getElementById("MainImgShow").attr("src"));
            int createDetail = detailsJpaContr.create(details);
            Element lstSizes = detailPage.getElementById("sizeChangeId");
            if (lstSizes != null) {
                for (Element e : lstSizes.getElementsByAttribute("data-se")) {
                    int sizeCreate = sizesContr.firstOrCreate(e.html());
                    detailSizeJpaContr.create(new DetailSize(sizeCreate, createDetail));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean checkVisited(String link) {
        VisitedController controller = new VisitedController();
        Visited findLink = controller.findLink(link);
        return findLink != null;
    }

    public static void addVisitLink(String link) {
        VisitedController controller = new VisitedController();
        Visited visited = new Visited();
        visited.setLink(link);
        controller.create(visited);
    }
}
