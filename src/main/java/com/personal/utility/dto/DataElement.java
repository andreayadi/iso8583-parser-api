package com.personal.utility.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataElement {
    private String field;
    private String usage;
    private Integer length;
    private String message;
}
