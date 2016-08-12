package com.appbusiness.chris.theappbusinesstest.domain.entitys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chris on 12/08/2016.
 */
public class ComicDataWrapper {

	@SerializedName("code")
	@Expose
	private int code;

	@SerializedName("status")
	@Expose
	private String status;

	@SerializedName("data")
	@Expose
	private Data data;

	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public Data getData() {
		return data;
	}
}
