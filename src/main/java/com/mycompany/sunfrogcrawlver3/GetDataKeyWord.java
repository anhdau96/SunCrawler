/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sunfrogcrawlver3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SaiBack
 */
public class GetDataKeyWord {

    public static void main(String[] args) {
//        MyDriver myDriver = new MyDriver();
//        PhantomJSDriver driver = myDriver.getDriver();
//        driver.get("https://nameberry.com/popular_names/US");
//        Document keyPage = Jsoup.parse(driver.getPageSource());
//        System.out.println(driver.getPageSource());
//        List<String> lstNames = new ArrayList<>();
//        Elements peopleNames = keyPage.getElementsByAttributeValueContaining("class", "name_link");
//        for (Element peopleName : peopleNames) {
//            lstNames.add("lstName.add(\"" + peopleName.html() + "\");");
////            System.out.println(peopleName.html());
//        }
//        Path file = Paths.get("data.txt");
//        try {
//            Files.write(file, lstNames, Charset.forName("UTF-8"));
//        } catch (IOException ex) {
//            Logger.getLogger(GetDataKeyWord.class.getName()).log(Level.SEVERE, null, ex);
//        }
        readFileVer1();
    }

    public static void readFile() {
        List<String> lstNames = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("names//yob2016.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                lstNames.add("lstName.add(\"" + line.split(",")[0] + "\");");
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(GetDataKeyWord.class.getName()).log(Level.SEVERE, null, ex);
        }
        Path file = Paths.get("data1.txt");
        try {
            Files.write(file, lstNames, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(GetDataKeyWord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static void readFileVer1() {
        List<String> lstNames = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data_v1.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                lstNames.add("lstName.add(\"" + line.trim() + "\");");
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(GetDataKeyWord.class.getName()).log(Level.SEVERE, null, ex);
        }
        Path file = Paths.get("lstdata_v1.txt");
        try {
            Files.write(file, lstNames, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(GetDataKeyWord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
