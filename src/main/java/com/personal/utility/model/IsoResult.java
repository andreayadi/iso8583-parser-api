package com.personal.utility.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.personal.utility.dto.DataElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "results")
public class IsoResult {
    private String isoMessage;
    private List<DataElement> data;
    private String createdDate = getCurrentDate();

    public IsoResult(String isoMessage, List<DataElement> data){
        this.isoMessage = isoMessage;
        this.data = data;   
    }

    public String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}