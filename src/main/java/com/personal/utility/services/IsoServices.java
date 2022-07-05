package com.personal.utility.services;

import com.personal.utility.dto.IsoMessage;
import com.personal.utility.exception.custom.CustomNullException;
import com.personal.utility.model.ResponseData;

public interface IsoServices {
    ResponseData<Object> parsingMessage(IsoMessage message) throws CustomNullException;
    String hex2Bin(String bitMessage);
    void manualAddData(String usage, Integer length, String valueMsg);
}
