package com.library.app.exception;

public enum Error {
	
	BOOK_NOT_FOUND(1L, "Kitap Bulunamadı."),
	CATEGORY_NOT_FOUND(2L, "Kategori Bulunamadı."),
	STORE_NOT_FOUND(3L, "Store Bulunamadı."),
	BOOK_OR_CATEGORY_NOT_FOUND(4L, "Store veya Kitap Bulunamadı."),
	BOOK_IS_ALREADY_EXIST(5L, "Kitap Zaten Mevcut."),
	CATEGORY_IS_ALREADY_EXIST(6L, "Kategori Zaten Mevcut."),
	STORE_IS_ALREADY_EXIST(7L, "Store Zaten Mevcut.");

	private Long errorCode;
	private String errorMessage;

	Error(Long errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Long getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
