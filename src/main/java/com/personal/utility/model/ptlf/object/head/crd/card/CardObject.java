package com.personal.utility.model.ptlf.object.head.crd.card;

import com.personal.utility.model.ptlf.object.head.crd.card.crdnum.CrdnumObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardObject {
    private CrdnumObject crd_num;
    private String mbr_num;
}
