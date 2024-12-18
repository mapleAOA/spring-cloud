package com.xyz.devicemonitoringservice.service.impl;

import com.xyz.devicemonitoringservice.beans.Device;
import com.xyz.devicemonitoringservice.service.DeviceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.xyz.devicemonitoringservice.util.datas.deviceDatabase;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Override
    public List getDeviceListDatas() {
        return new ArrayList<>(deviceDatabase.values());
    }

    @Override
    public Device getDeviceById(String id) {
        return deviceDatabase.get(id);
    }

    @Override
    public String fixNeed(String id) {
        Device device=deviceDatabase.get(id);
        String ans="不需要修理";
        if(!device.isWorkFlag()){
            //接下来是根据数据进行处理
            ans="需要修理";
        }
        return ans;
    }
}
