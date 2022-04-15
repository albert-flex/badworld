package com.albertflex.blacksky.common;

import lombok.Data;

@Data
public class RResult{
    private Long errorCode;
    private String errorMsg;
    private Boolean success;

    public RResult(Long errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        success=false;
    }

    public RResult() {
        success=true;
        errorCode=0L;
        errorMsg="";
    }
}
