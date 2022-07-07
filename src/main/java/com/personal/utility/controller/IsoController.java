package com.personal.utility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.utility.dto.IsoMessage;
import com.personal.utility.dto.ResponseData;
import com.personal.utility.exception.custom.CustomNullException;
import com.personal.utility.services.IsoServices;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/iso")
public class IsoController {
    
    private ResponseData<Object> responseData;

    @Autowired
    private IsoServices isoServices;

    @PostMapping
    public ResponseEntity<?> parseMessage(@RequestBody IsoMessage message) throws CustomNullException{
        responseData = isoServices.parsingMessage(message);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @GetMapping
    public ResponseEntity<?> getAllMessage(){
        responseData = isoServices.getAllData();
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}
