package com.personal.utility.model.ptlf.object.head.crd.term;

import com.personal.utility.model.ptlf.object.TimeObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TermObject {
    private TimeObject tim;
    private String ln;
    private String fiid;
    private String term_id;
}
