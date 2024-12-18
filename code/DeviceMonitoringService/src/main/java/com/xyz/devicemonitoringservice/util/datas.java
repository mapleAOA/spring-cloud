package com.xyz.devicemonitoringservice.util;


import com.xyz.devicemonitoringservice.beans.Device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class datas {
    public static Map<String, Device> deviceDatabase = new HashMap<>();
    static {
        Device device01=new Device("1",true,"01datas");
        Device device02=new Device("2",false,"02datas");
        deviceDatabase.put(device01.getId(),device01);
        deviceDatabase.put(device02.getId(),device02);
    }
}
