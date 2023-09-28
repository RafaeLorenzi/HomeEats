package com.delivery.homeeats.api.model.mixin;

import com.delivery.homeeats.domain.model.District;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class MunicipalitiesMixin {
	
	@JsonIgnoreProperties(value = "name", allowGetters = true)
	private District district;

}
