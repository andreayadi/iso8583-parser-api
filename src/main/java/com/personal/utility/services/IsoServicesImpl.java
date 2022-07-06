package com.personal.utility.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.utility.dto.DataElement;
import com.personal.utility.dto.Elements;
import com.personal.utility.dto.IsoMessage;
import com.personal.utility.dto.ResponseData;
import com.personal.utility.exception.custom.CustomNullException;
import com.personal.utility.model.IsoResult;
import com.personal.utility.repository.IsoResultRepository;
import com.personal.utility.util.Helper;
import com.personal.utility.util.ReadJsonFiles;
import com.personal.utility.validation.IsoValidator;

import lombok.NoArgsConstructor;

@Service
@Transactional
@NoArgsConstructor
public class IsoServicesImpl implements IsoServices {

    private ResponseData<Object> responseData;

    // Storing bit active result
    private List<DataElement> allData;
    private DataElement dataElement;

    // Reading Json Files
    private ReadJsonFiles jsonData = new ReadJsonFiles();
    private List<Elements> elements = jsonData.getIsoElements();

    private Helper helper = new Helper();

    @Autowired
    private IsoValidator isoValidator;

    @Autowired
    private IsoResultRepository isoResultRepository;

    @Override
    public ResponseData<Object> parsingMessage(IsoMessage message) throws CustomNullException {
      IsoResult messageResult;
      String dataMessage, messageParse, bitActive, dataUsage;
      Integer lengthNeed, dataField, dataLength;

      dataMessage = message.getMessage();

      messageParse = dataMessage;

      isoValidator.messageValidation(dataMessage);

      allData = new ArrayList<>();
      
      try {
        // Checking if message had header
        if (messageParse.substring(0,3).equalsIgnoreCase("ISO")) {
          manualAddData("Header", 12, messageParse.substring(0,12));
          messageParse = messageParse.substring(12);
          System.out.println("yes header");
        }
        
        // Adding bit active and message without field to allData
        manualAddData("Message Type Identifier (MTI)", 4, messageParse.substring(0,4));
        messageParse = messageParse.substring(4);
        manualAddData("Primary Bitmap", 16, messageParse.substring(0,16));

        // Get binary value from primary bitmap
        bitActive = helper.hex2Bin(messageParse.substring(0,16)) ;

        // Remove used message
        messageParse = messageParse.substring(16);
        
        if(bitActive.charAt(0) == '1'){
            bitActive += helper.hex2Bin(messageParse.substring(0,16));
        }
        
        for(int i = 0; i<bitActive.length(); i++){
            
            if(bitActive.charAt(i) == '1'){
                
                Elements element = elements.get(i);
                dataField = element.getField();
                dataUsage = element.getUsage();
                dataLength = element.getLength();

                if(element.getFixed()){
                    dataElement = new DataElement(dataField, dataUsage, dataLength, messageParse.substring(0,dataLength));
                    allData.add(dataElement);
                    messageParse = messageParse.substring(dataLength);
                } else {
                    lengthNeed = Integer.parseInt(messageParse.substring(0, dataLength));
                    dataElement = new DataElement(dataField, dataUsage, lengthNeed, messageParse.substring(dataLength,lengthNeed + dataLength));
                    allData.add(dataElement);
                    messageParse = messageParse.substring(dataLength + lengthNeed);
                }
            } 

        }
        
        // Final parse result for response
        try {
          messageResult = new IsoResult(dataMessage, allData);
  
          isoResultRepository.save(messageResult);
  
          responseData = new ResponseData<>(200, "Message Parsed Succesfully", messageResult);
          return responseData;
        } catch (Exception e) {
          responseData = new ResponseData<>(500, "Failed To Save to Database", null);
          return responseData;
        }

      } catch (Exception e) {
        responseData = new ResponseData<>(400, "Wrong Format Message", null);
        return responseData;
      } 
    }
     
    @Override
    public void manualAddData(String usage, Integer length, String valueMsg) {
        dataElement = new DataElement(null, usage, length, valueMsg);
        allData.add(dataElement);
    }  
}