package com.personal.utility.model.ptlf;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "testing")
public class PtlfResult {
    
    private String id;
    private String firstName;
    private String lastName;

    public PtlfResult(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
