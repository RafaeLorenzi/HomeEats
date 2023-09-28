package com.delivery.homeeats.core.jackson;

import org.springframework.stereotype.Component;

import com.delivery.homeeats.api.model.mixin.KitchenMixin;
import com.delivery.homeeats.api.model.mixin.MunicipalitiesMixin;
import com.delivery.homeeats.api.model.mixin.RestaurantMixin;
import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.model.Municipalities;
import com.delivery.homeeats.domain.model.Restaurant;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {
	
	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModule() {
		setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
		setMixInAnnotation(Municipalities.class, MunicipalitiesMixin.class);
		setMixInAnnotation(Kitchen.class, KitchenMixin.class);
	}
	
	

}
