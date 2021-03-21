package com.library.app.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreBookDTO {

	private Long id;
	@NotNull(message = "Kitap Bilgisi Girilmelidir")
	private Long bookId;
//	private BookDTO book;
	@NotNull(message = "Store Bilgisi Girilmelidir")
	private Long storeId;
//	private StoreDTO store;
	@NotNull(message = "Kitap Ücreti Bilgisi Girilmelidir")
	private BigDecimal price;
	private Date insertDate;
	private Date updateDate;
	
}
