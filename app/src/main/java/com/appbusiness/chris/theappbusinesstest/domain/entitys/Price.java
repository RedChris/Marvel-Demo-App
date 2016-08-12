package com.appbusiness.chris.theappbusinesstest.domain.entitys;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chris on 12/08/2016.
 */
public class Price {

	@SerializedName("type")
	public String type;

	@SerializedName("price")
	public Double price;

	public String getType() {
		return type;
	}

	public Double getPrice() {
		return price;
	}
}
