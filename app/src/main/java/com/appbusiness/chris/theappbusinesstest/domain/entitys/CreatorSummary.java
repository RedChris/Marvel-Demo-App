package com.appbusiness.chris.theappbusinesstest.domain.entitys;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Chris on 12/08/2016.
 */
public class CreatorSummary {

	@SerializedName("name")
	private String name;

	@SerializedName("role")
	private String role;

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}
}
