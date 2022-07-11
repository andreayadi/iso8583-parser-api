package com.personal.utility.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.utility.dto.ResponseData;

@Service
@Transactional
public class PtlfServicesImpl implements PtlfServices {

    ResponseData<Object> responseData;

    @Override
    public ResponseData<Object> getDataPtlf() {

    responseData = new ResponseData<>(200, "Success", null);
    return responseData;
    }
    
}
