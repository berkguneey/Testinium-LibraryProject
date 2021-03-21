package com.library.app.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateRequest {

	@NotNull(message = "Güncellenmek İstenilen Kategori Bilgisi Girilmelidir")
	private Long categoryId;
	
}
