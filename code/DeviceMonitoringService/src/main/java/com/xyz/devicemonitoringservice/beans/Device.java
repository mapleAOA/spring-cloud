package com.xyz.devicemonitoringservice.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {
    private String id;
    private boolean workFlag;
    private String diary;
}
