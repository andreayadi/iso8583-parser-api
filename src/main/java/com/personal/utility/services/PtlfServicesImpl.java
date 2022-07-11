package com.personal.utility.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.utility.dto.PtlfDate;
import com.personal.utility.dto.ResponseData;

@Service
@Transactional
public class PtlfServicesImpl implements PtlfServices {

    ResponseData<Object> responseData;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public ResponseData<Object> getDataPtlf(PtlfDate date) {
        
    Boolean dateSelected = mongoTemplate.collectionExists(date.getDate());
        
    responseData = new ResponseData<>(200, "Success", dateSelected);
    return responseData;
    }
    
}
