package com.personal.utility.model.utility;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.personal.utility.dto.DataElement;
import com.personal.utility.util.Helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "results")
public class IsoResult {

    private String id;
    private String isoMessage;
    private List<DataElement> data;
    private String createdDate;
    
    public IsoResult(String isoMessage, List<DataElement> data){
        Helper helper = new Helper();

        this.isoMessage = isoMessage;
        this.data = data;   
        this.createdDate = helper.getDate();
    }

}