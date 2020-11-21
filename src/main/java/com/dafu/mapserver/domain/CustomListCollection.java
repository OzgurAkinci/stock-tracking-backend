package com.dafu.mapserver.domain;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomListCollection<T> {
	private Long totalCount;
	private Collection<T> data;
}
