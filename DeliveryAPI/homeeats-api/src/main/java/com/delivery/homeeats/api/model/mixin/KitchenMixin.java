package com.delivery.homeeats.api.model.mixin;

import java.util.List;

import com.delivery.homeeats.domain.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class KitchenMixin {

	@JsonIgnore
	private List<Restaurant> restaurants;
}
