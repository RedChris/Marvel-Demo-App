package com.appbusiness.chris.theappbusinesstest.domain.entitys;


import android.support.annotation.Nullable;

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

	//Only populated when there is an error
	@SerializedName("message")
	private String message;

	@Nullable
	public Integer getCode() {
		return code;
	}

	@Nullable
	public String getStatus() {
		return status;
	}

	@Nullable
	public Data getData() {
		return data;
	}

	@Nullable
	public String getErrorMessage() {
		return message;
	}
}
