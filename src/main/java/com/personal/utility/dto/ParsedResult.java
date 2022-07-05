package com.personal.utility.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParsedResult {
    private String isoMsg;
    private List<DataElement> data;
}
