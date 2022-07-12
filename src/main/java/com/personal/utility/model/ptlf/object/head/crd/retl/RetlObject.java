package com.personal.utility.model.ptlf.object.head.crd.retl;

import com.personal.utility.model.ptlf.object.head.crd.retl.ky.KyObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RetlObject {
    private KyObject ky;
    private String term_id;
    private String shift_num;
    private String batch_num;
}
