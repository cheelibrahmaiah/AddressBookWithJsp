package com.address.book.app.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String message;
    private boolean success;

    public static ResponseDto prepareResponse(String message, boolean isSuccess) {
        return new ResponseDto(message, isSuccess);
    }
}
