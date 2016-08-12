package com.appbusiness.chris.theappbusinesstest.ui.comic.models;

import com.appbusiness.chris.theappbusinesstest.domain.entitys.Comic;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.CreatorSummary;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.Creators;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.Price;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.Thumbnail;

import java.util.Collections;
import java.util.List;

/**
 * Created by Chris on 11/08/2016.
 */
public class ComicModel {
	public static final String KEY_COMIC_ID = "KeyComicId";

	private final String IMAGE_TYPE_PATH = "/portrait_small.";

	private final Integer id;
	private final String title;
	private final String description;
	private final String issueNumber;
	private final Integer pageCount;
	private final Price price;
	private final List<CreatorSummary> creators;
	private final String thumbnailUrl;

	public ComicModel(Comic comic) {
		id = comic.getId();
		title = comic.getTitle();

		String variantDescription = comic.getVariantDescription();
		if (variantDescription != null) {
			description = variantDescription;
		} else {
			description = comic.getDescription();
		}

		Integer issueNumber = comic.getIssueNumber();
		this.issueNumber = (issueNumber == null)? "?" : issueNumber.toString();
		pageCount = comic.getPageCount();

		price = getBestPriceFromList(comic.getPrices());

		Creators creators = comic.getCreators();
		if (creators != null) {
			this.creators = creators.getCreatorSummaries();
		} else {
			this.creators = Collections.emptyList();
		}

		thumbnailUrl = getThumbnailPath(comic.getThumbnail());
	}

	private Price getBestPriceFromList(List<Price> prices) {
		for (Price price : prices) {
			if (price != null && price.getPrice() != null) {
				return price;
			}
		}
		return null;
	}

	private String getThumbnailPath(Thumbnail thumbnail) {
		if (thumbnail != null
				&& thumbnail.getExtension() != null
				&& thumbnail.getPath() != null) {
			return thumbnail.getPath() + IMAGE_TYPE_PATH + thumbnail.getExtension();
		}
		return null;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public Price getPrice() {
		return price;
	}

	public List<CreatorSummary> getCreators() {
		return creators;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
}
