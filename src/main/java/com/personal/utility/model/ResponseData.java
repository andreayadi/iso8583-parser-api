package com.personal.utility.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
    public Integer status;
    public String message;
    private T data;
}
