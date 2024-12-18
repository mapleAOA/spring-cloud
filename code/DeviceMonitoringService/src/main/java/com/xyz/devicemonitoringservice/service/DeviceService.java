package com.xyz.devicemonitoringservice.service;

import com.xyz.devicemonitoringservice.beans.Device;

import java.util.List;

public interface DeviceService {
    public List getDeviceListDatas();
    public Device getDeviceById(String id);
    public String fixNeed(String id);
}
