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
    
    private ReadJsonFiles jsonData = new ReadJsonFiles();

    @Autowired
    private IsoValidator isoValidator;

    @Override
    public ResponseData<Object> parsingMessage(IsoMessage message) throws CustomNullException {
        // Iso message is empty?
        isoValidator.messageValidation(message.getMessage());
        
        // Get bit elements array
        List<Elements> elements = jsonData.getIsoElements();

        // Storing all bit active and message
        List<DataElement> allData = new ArrayList<>();
        
        // Adding bit active and message to allData
        dataElement = new DataElement("", "Bitmap", 16, "0000000000000000");
        allData.add(dataElement);
        dataElement = new DataElement("2", "Bitmap", 16, "0000000000000000");
        allData.add(dataElement);

        System.out.println(hex2Bin("FFFF"));

        // Final parse result for response
        parsedResult = new ParsedResult(message.getMessage(), allData);
        responseData = new ResponseData<>(200, "Success i guess", parsedResult);
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


    
}
