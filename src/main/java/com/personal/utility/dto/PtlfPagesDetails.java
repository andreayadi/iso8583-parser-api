package com.personal.utility.dto;

import java.util.List;

import com.personal.utility.model.ptlf.PtlfResult;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PtlfPagesDetails {
    private Integer pagesShowed;
    private Integer totalPages;
    private Integer totalElements;
    private List<PtlfResult> contents;
}
