package com.personal.utility.services;

import org.springframework.data.domain.Pageable;

import com.personal.utility.dto.IsoMessage;
import com.personal.utility.dto.ResponseData;
import com.personal.utility.exception.custom.CustomNullException;

public interface IsoServices {
    ResponseData<Object> parsingMessage(IsoMessage message) throws CustomNullException;
    ResponseData<Object> deleteMessage(IsoMessage message) throws CustomNullException;
    ResponseData<Object> getAllData();
    ResponseData<Object> getAllDataPage(Pageable reqPage);
    void manualAddData(String usage, Integer length, String valueMsg);
}
