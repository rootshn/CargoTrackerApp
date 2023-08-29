package com.ptt.cargoAdressSorter.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse {
    private int status = 0 ;
    private Object data;
    private String message;
}
