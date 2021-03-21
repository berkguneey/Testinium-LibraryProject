package com.library.app.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BaseDataResponse<T> extends BaseResponse {

	private T data;

    public BaseDataResponse(boolean status, String message, T data) {
        super(status, message);
        this.data = data;
    }

    public BaseDataResponse(boolean status, T data) {
        super(status);
        this.data = data;
    }
	
}
