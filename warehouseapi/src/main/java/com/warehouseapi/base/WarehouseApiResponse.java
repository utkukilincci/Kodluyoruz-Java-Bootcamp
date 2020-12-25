package com.warehouseapi.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class WarehouseApiResponse<T> {

    private T responseData;
    private HttpStatus httpStatus;
    private WarehouseApiResponseError error;

    public WarehouseApiResponse(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public WarehouseApiResponse(T responseData, HttpStatus httpStatus) {
        this.responseData = responseData;
        this.httpStatus = httpStatus;
    }

    public WarehouseApiResponse(HttpStatus httpStatus,
                                      WarehouseApiResponseError error) {
        this.httpStatus = httpStatus;
        this.error = error;
    }
}
