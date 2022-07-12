package com.personal.utility.services;

import org.springframework.data.domain.Pageable;

import com.personal.utility.dto.ResponseData;
import com.personal.utility.exception.custom.CustomNullException;

public interface PtlfServices {
    ResponseData<Object> getDataPtlf(Pageable reqPage, String date) throws CustomNullException;
}
