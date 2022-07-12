package com.personal.utility.model.ptlf.object.token;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenObject {
    @Field("id")
    private String id;
    private Integer length;
    private String data;

}
