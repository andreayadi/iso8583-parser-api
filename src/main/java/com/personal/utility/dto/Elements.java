package com.personal.utility.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Elements {
    private Integer field;
    private String type;
    private Boolean fixed;
    private Integer length;
    private Integer maxLength;
    private String usage;
}