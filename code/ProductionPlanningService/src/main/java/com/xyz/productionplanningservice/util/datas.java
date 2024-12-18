package com.xyz.productionplanningservice.util;

import com.xyz.productionplanningservice.beans.Plan;

import java.util.HashMap;
import java.util.Map;

public class datas {
    public static Map<String, Plan> planDatabase = new HashMap<>();
    static {
        Plan plan01=new Plan("1","this is planA","a开始时间","a结束时间");
        Plan plan02=new Plan("2","this is planB","b开始时间","b结束时间");
        planDatabase.put(plan01.getId(),plan01);
        planDatabase.put(plan02.getId(),plan02);
    }
}
