package com.personal.utility.model.ptlf;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.personal.utility.model.ptlf.object.auth.AuthObject;
import com.personal.utility.model.ptlf.object.head.HeadObject;
import com.personal.utility.model.ptlf.object.token.TokenObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "testing")
public class PtlfResult {
    
    private String id;
    private HeadObject head;
    private AuthObject auth;
    private List<TokenObject> token;
}
 
