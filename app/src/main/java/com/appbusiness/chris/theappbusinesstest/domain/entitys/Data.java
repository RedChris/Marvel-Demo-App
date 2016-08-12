package com.appbusiness.chris.theappbusinesstest.domain.entitys;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 12/08/2016.
 */

public class Data {

	@SerializedName("offset")
	private Integer offset;

	@SerializedName("limit")
	private Integer limit;

	@SerializedName("total")
	private Integer total;

	@SerializedName("count")
	private Integer count;

	@SerializedName("results")
	private List<Comic> comics = new ArrayList<>();

	public Integer getOffset() {
		return offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public Integer getTotal() {
		return total;
	}

	public Integer getCount() {
		return count;
	}

	public List<Comic> getComics() {
		return comics;
	}
}