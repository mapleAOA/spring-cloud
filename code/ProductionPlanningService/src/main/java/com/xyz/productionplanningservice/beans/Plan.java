package com.xyz.productionplanningservice.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plan {
    private String id;
    private String content;
    private String startTime;
    private String endTime;
}
