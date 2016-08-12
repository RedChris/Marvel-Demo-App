package com.appbusiness.chris.theappbusinesstest.domain.entitys;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Chris on 12/08/2016.
 */
public class ComicDataWrapper {

	@SerializedName("code")
	private Integer code;

	@SerializedName("status")
	private String status;

	@SerializedName("data")
	private Data data;

	public Integer getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public Data getData() {
		return data;
	}
}
