package com.personal.utility.model.ptlf.object.auth.authobj;

import com.personal.utility.model.ptlf.object.DateObject;
import com.personal.utility.model.ptlf.object.TimeObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PreauthObject {
    private DateObject dat;
    private TimeObject tim;
}
