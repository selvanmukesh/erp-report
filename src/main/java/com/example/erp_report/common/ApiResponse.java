package com.example.erp_report.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T> {
    public T data;
    public String error;
    public int status;
    public String message;

}
