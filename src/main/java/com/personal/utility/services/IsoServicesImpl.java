package com.personal.utility.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.utility.dto.DataElement;
import com.personal.utility.dto.Elements;
import com.personal.utility.dto.IsoMessage;
import com.personal.utility.dto.ParsedResult;
import com.personal.utility.exception.custom.CustomNullException;
import com.personal.utility.model.ResponseData;
import com.personal.utility.util.ReadJsonFiles;
import com.personal.utility.validator.IsoValidator;

import lombok.NoArgsConstructor;

@Service
@Transactional
@NoArgsConstructor
public class IsoServicesImpl implements IsoServices {

    private ResponseData<Object> responseData;

    private ParsedResult parsedResult;

    private DataElement dataElement;

    private List<DataElement> allData;
    
    private ReadJsonFiles jsonData = new ReadJsonFiles();

    private List<Elements> elements = jsonData.getIsoElements();

    private String messageParse, bitActive;

    private Integer lengthNeed;

    @Autowired
    private IsoValidator isoValidator;

    @Override
    public ResponseData<Object> parsingMessage(IsoMessage message) throws CustomNullException {
        // Duplicate message to parse
        messageParse = message.getMessage();

        // Iso message is empty?
        isoValidator.messageValidation(message.getMessage());

        // Storing all bit active and message
        allData = new ArrayList<>();
        
        // Adding bit active and message to allData
        manualAddData("Header", 12, messageParse.substring(0,12));
        messageParse = messageParse.substring(12);
        manualAddData("Message Type Identifier (MTI)", 4, messageParse.substring(0,4));
        messageParse = messageParse.substring(4);
        manualAddData("Primary Bitmap", 16, messageParse.substring(0,16));

        // Get binary value from primary bitmap
        bitActive = hex2Bin(messageParse.substring(0,16)) ;

        // Remove used message
        messageParse = messageParse.substring(16);
        
        if(bitActive.charAt(0) == '1'){
            bitActive += hex2Bin(messageParse.substring(0,16));
        }
        
        for(int i = 0; i<bitActive.length(); i++){
            
            if(bitActive.charAt(i) == '1'){
                
                Elements element = elements.get(i);
                
                if(element.getFixed()){
                    dataElement = new DataElement(element.getField(), element.getUsage(), element.getLength(), messageParse.substring(0,element.getLength()));
                    allData.add(dataElement);
                    messageParse = messageParse.substring(element.getLength());
                } else {
                    lengthNeed = Integer.parseInt(messageParse.substring(0, element.getLength()));
                    dataElement = new DataElement(element.getField(), element.getUsage(), lengthNeed, messageParse.substring(element.getLength(),lengthNeed + element.getLength()));
                    allData.add(dataElement);
                    messageParse = messageParse.substring(element.getLength() + lengthNeed);
                }
            } 

        }
        // Final parse result for response
        parsedResult = new ParsedResult(message.getMessage(), allData);
        responseData = new ResponseData<>(200, "Parsing Success", parsedResult);
        return responseData;
    }
     
    @Override
    public String hex2Bin(String bitMessage) {
    String out = "";

    for(int i=0; i<bitMessage.length(); i++){
        switch (bitMessage.charAt(i)) {
        case '0':
        out += "0000";
        break;
      case '1':
        out += "0001";
        break;
      case '2':
        out += "0010";
        break;
      case '3':
        out += "0011";
        break;
      case '4':
        out += "0100";
        break;
      case '5':
        out += "0101";
        break;
      case '6':
        out += "0110";
        break;
      case '7':
        out += "0111";
        break;
      case '8':
        out += "1000";
        break;
      case '9':
        out += "1001";
        break;
      case 'A':
        out += "1010";
        break;
      case 'B':
        out += "1011";
        break;
      case 'C':
        out += "1100";
        break;
      case 'D':
        out += "1101";
        break;
      case 'E':
        out += "1110";
        break;
      case 'F':
        out += "1111";
        break;
      default:
        return "";
        }
    }
    return out;
    }

    @Override
    public void manualAddData(String usage, Integer length, String valueMsg) {
        dataElement = new DataElement(null, usage, length, valueMsg);
        allData.add(dataElement);
    }  
}