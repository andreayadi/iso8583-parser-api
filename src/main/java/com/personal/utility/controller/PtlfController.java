package com.personal.utility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.utility.dto.PtlfById;
import com.personal.utility.dto.ResponseData;
import com.personal.utility.exception.custom.CustomNullException;
import com.personal.utility.services.PtlfServices;

// @CrossOrigin("http://localhost:3000")
@CrossOrigin("http://10.20.231.227:3000")
@RestController
@RequestMapping("/ptlf")
public class PtlfController {

    private ResponseData<Object> responseData;

    @Autowired
    private PtlfServices ptlfServices;

    @GetMapping
    public ResponseEntity<?> getAllData(Pageable reqPage, @RequestParam String date) throws CustomNullException {
        responseData = ptlfServices.getAllPtlf(reqPage, date);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @PostMapping
    public ResponseEntity<?> getAllDataById(Pageable reqPage, @RequestBody PtlfById searchData)
            throws CustomNullException {
        responseData = ptlfServices.getAllPtlfById(reqPage, searchData);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @GetMapping("/{date}/{id}")
    public ResponseEntity<?> getDataById(@PathVariable String date, @PathVariable String id)
            throws CustomNullException {
        responseData = ptlfServices.getDataPtlfId(date, id);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}
