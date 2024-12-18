package com.xyz.qualitycontrolservice.util;


import com.xyz.qualitycontrolservice.beans.Product;

import java.util.HashMap;
import java.util.Map;

public class datas {
    public static Map<String, Product> ProductDatabase = new HashMap<>();
    static {
        Product product01=new Product("1",true);
        Product product02=new Product("2",false);
        ProductDatabase.put(product01.getId(),product01);
        ProductDatabase.put(product02.getId(),product02);
    }
}
