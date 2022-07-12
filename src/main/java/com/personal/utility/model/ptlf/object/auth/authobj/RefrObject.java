package com.personal.utility.model.ptlf.object.auth.authobj;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefrObject {
 private String imp_ind;
 private String avail_bal;
 private String ledg_bal;
 private String amt_on_hold;
 private String ttl_float;
 private String cur_float;
}
