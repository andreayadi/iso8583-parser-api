package com.personal.utility.model.ptlf.object.head;

import com.personal.utility.model.ptlf.object.head.crd.CrdObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeadObject {
    private CrdObject crd;
    private String dat_tim;
    private String rec_typ;
}
