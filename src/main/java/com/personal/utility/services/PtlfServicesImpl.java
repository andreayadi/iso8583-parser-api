package com.personal.utility.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.utility.dto.PtlfDate;
import com.personal.utility.dto.ResponseData;
import com.personal.utility.model.ptlf.PtlfResult;

@Service
@Transactional
public class PtlfServicesImpl implements PtlfServices {

    ResponseData<Object> responseData;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public ResponseData<Object> getDataPtlf(PtlfDate date) {
        
    PtlfResult testing = mongoTemplate.findById("62c46e11fb8e91e1c11f83be", PtlfResult.class, "220706");
    
    responseData = new ResponseData<>(200, "Success", testing);
    return responseData;
    }
    
}
