package com.library.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class BaseMapper {

	protected static final ModelMapper modelMapper = new ModelMapper();
	
	private BaseMapper() {
		
	}
	
	public static <T> T map(Object source, Class<T> destinationType) {
		return modelMapper.map(source, destinationType);
	}

	public static <T> List<T> mapAll(List<?> source, Class<T> destinationType) {
		List<T> mappedList = new ArrayList<>(source.size());
		for (Object obj : source) {
			mappedList.add(map(obj, destinationType));
		}
		return mappedList;
	}
}
