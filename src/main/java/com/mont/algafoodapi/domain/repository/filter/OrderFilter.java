package com.mont.algafoodapi.domain.repository.filter;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderFilter {

	private Long clientId;
	private Long restaurantId;
	
	private OffsetDateTime creationDateInit;
	
	private OffsetDateTime creationDateEnd;
	
}