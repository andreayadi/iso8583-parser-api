package com.personal.utility.model.ptlf.object.head.crd.retl.ky.rdfkey;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RdfkeyObject {
    private String fiid;
    private String grp;
    private String regn;
    @Field("id")
    private String id;
}
