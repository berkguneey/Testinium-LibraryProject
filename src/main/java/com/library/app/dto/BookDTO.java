package com.library.app.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
	
	private Long id;
	@NotNull(message = "Kitap Adı Bilgisi Girilmelidir")
	private String name;
	@NotNull(message = "Kitap Kategorisi Bilgisi Girilmelidir")
	private Long categoryId;
	//private CategoryDTO category;
	private Date insertDate;
	private Date updateDate;
	
}
