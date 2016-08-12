package com.appbusiness.chris.theappbusinesstest.domain.entitys;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 11/08/2016.
 */
public class Comic {

	@SerializedName("id")
	private Integer id;

	@SerializedName("digitalId")
	private Integer digitalId;

	@SerializedName("title")
	private String title;

	@SerializedName("issueNumber")
	private Integer issueNumber;

	@SerializedName("variantDescription")
	private String variantDescription;

	@SerializedName("description")
	private String description;

	@SerializedName("format")
	private String format;

	@SerializedName("pageCount")
	private Integer pageCount;

	@SerializedName("resourceURI")
	private String resourceURI;

	@SerializedName("prices")
	private List<Price> prices = new ArrayList<Price>();

	@SerializedName("thumbnail")
	private Thumbnail thumbnail;

	@SerializedName("creators")
	private Creators creators;

	public Integer getId() {
		return id;
	}

	public Integer getDigitalId() {
		return digitalId;
	}

	public String getTitle() {
		return title;
	}

	public Integer getIssueNumber() {
		return issueNumber;
	}

	public String getVariantDescription() {
		return variantDescription;
	}

	public String getDescription() {
		return description;
	}

	public String getFormat() {
		return format;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public Thumbnail getThumbnail() {
		return thumbnail;
	}

	public Creators getCreators() {
		return creators;
	}
}
