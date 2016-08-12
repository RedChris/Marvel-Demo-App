package com.appbusiness.chris.theappbusinesstest.domain.entitys;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 12/08/2016.
 */
public class Creators {

	@SerializedName("items")
	public List<CreatorSummary> items = new ArrayList<>();

	public List<CreatorSummary> getItems() {
		return items;
	}
}
