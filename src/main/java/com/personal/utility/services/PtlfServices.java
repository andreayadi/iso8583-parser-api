package com.personal.utility.services;

import com.personal.utility.dto.PtlfDate;
import com.personal.utility.dto.ResponseData;

public interface PtlfServices {
    ResponseData<Object> getDataPtlf(PtlfDate date);
    
}
