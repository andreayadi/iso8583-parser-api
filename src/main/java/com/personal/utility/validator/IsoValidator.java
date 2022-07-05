package com.personal.utility.validator;

import org.springframework.stereotype.Service;

import com.personal.utility.exception.custom.CustomNullException;

@Service
public class IsoValidator {
    
    public void messageValidation(String message) throws CustomNullException{
        if(message.isEmpty()) {
            throw new CustomNullException("Message is not Valid");
        }
    }
}
