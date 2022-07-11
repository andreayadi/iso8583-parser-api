package com.personal.utility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.utility.dto.PtlfDate;
import com.personal.utility.dto.ResponseData;
import com.personal.utility.services.PtlfServices;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ptlf")
public class PtlfController {
    
    private ResponseData<Object> responseData;
    
    @Autowired
    private PtlfServices ptlfServices;

    @PostMapping
    public ResponseEntity<?> getData(@RequestBody PtlfDate date){
        responseData =  ptlfServices.getDataPtlf(date);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}
