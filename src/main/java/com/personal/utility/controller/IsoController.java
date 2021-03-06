package com.personal.utility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.utility.dto.IsoMessage;
import com.personal.utility.dto.ResponseData;
import com.personal.utility.exception.custom.CustomNullException;
import com.personal.utility.services.IsoServices;

// @CrossOrigin("http://localhost:3000")
@CrossOrigin("http://10.20.231.227:3000")
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
    
    @GetMapping("/all")
    public ResponseEntity<?> getAllMessage(){
        responseData = isoServices.getAllData();
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMessage(@RequestBody IsoMessage message) throws CustomNullException{
        responseData = isoServices.deleteMessage(message);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
    
    @GetMapping
    public ResponseEntity<?> getAllPage(Pageable page){
        responseData = isoServices.getAllDataPage(page);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}
