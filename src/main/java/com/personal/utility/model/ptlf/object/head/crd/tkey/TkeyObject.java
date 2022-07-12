package com.personal.utility.model.ptlf.object.head.crd.tkey;

import com.personal.utility.model.ptlf.object.head.crd.tkey.rkey.RkeyObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TkeyObject {
    private RkeyObject rkey;
    private String term_id;
}
