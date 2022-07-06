package com.personal.utility.services;

import com.personal.utility.dto.IsoMessage;
import com.personal.utility.dto.ResponseData;
import com.personal.utility.exception.custom.CustomNullException;

public interface IsoServices {
    ResponseData<Object> parsingMessage(IsoMessage message) throws CustomNullException;
    void manualAddData(String usage, Integer length, String valueMsg);
}
