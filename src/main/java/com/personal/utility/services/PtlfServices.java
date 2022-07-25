package com.personal.utility.services;

import org.springframework.data.domain.Pageable;

import com.personal.utility.dto.PtlfById;
import com.personal.utility.dto.ResponseData;
import com.personal.utility.exception.custom.CustomNullException;

public interface PtlfServices {
    ResponseData<Object> getAllPtlf(Pageable reqPage, String date) throws CustomNullException;

    ResponseData<Object> getAllPtlfById(Pageable reqPage, PtlfById searchData) throws CustomNullException;

    ResponseData<Object> getDataPtlfId(String date, String id) throws CustomNullException;
}
