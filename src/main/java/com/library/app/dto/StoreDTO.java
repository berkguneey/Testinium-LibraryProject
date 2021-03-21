package com.library.app.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDTO {

	private Long id;
	@NotNull(message = "Store Bilgisi Girilmelidir")
	private String name;
	@NotNull(message = "Şehir Bilgisi Girilmelidir")
	private String city;
	private Date insertDate;
	private Date updateDate;
	
}
