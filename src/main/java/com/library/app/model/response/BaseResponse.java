package com.library.app.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class BaseResponse {

	private boolean status;
    private String message;

    public BaseResponse(boolean status) {
        this.status = status;
    }

    public BaseResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
	
}
