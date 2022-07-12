package com.personal.utility.model.ptlf.object.head.crd;

import com.personal.utility.model.ptlf.object.head.crd.card.CardObject;
import com.personal.utility.model.ptlf.object.head.crd.retl.RetlObject;
import com.personal.utility.model.ptlf.object.head.crd.term.TermObject;
import com.personal.utility.model.ptlf.object.head.crd.tkey.TkeyObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrdObject {
    private CardObject card;
    private RetlObject retl;
    private TermObject term;
    private TkeyObject tkey;
    private String ln;
    private String fiid;
    private String data_flag;
}
