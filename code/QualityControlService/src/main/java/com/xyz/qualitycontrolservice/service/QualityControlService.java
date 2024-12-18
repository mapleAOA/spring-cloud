package com.xyz.qualitycontrolservice.service;

import java.util.List;

public interface QualityControlService {
     public String dealError(String id);
     public List recordError();
     public String errorReport();
}
