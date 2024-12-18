package com.xyz.qualitycontrolservice.service.impl;

import com.xyz.qualitycontrolservice.beans.Product;
import com.xyz.qualitycontrolservice.service.QualityControlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.xyz.qualitycontrolservice.util.datas.ProductDatabase;
@Service
public class QualityControlServiceImpl implements QualityControlService {

    boolean deal(String id){return true;}
    @Override
    public String dealError(String id) {
        if(deal(id)){
            //处理
            return "处理成功";
        }
        return "处理失败";
    }

    @Override
    public List recordError() {
        List list01=new ArrayList<>(ProductDatabase.values());
        Iterator<Product> iterator = list01.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.isOk()) {
                iterator.remove(); // 删除当前元素
            }
        }
        return list01;
    }

    @Override
    public String errorReport() {
        int trueCount = 0;
        int falseCount = 0;
        // 遍历 ProductDatabase 的值
        for (Product product : ProductDatabase.values()) {
            if (product.isOk()) {
                trueCount++;
            } else {
                falseCount++;
            }
        }
        // 返回结果字符串
        return "Number of product with isOk=true: " + trueCount +
                ", Number of product with isOk=false: " + falseCount;
    }
}
