package com.library.app.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
	
	private Long id;
	@NotNull(message = "Kategori Bilgisi Girilmelidir")
	private String name;
	private Date insertDate;
	private Date updateDate;
}
