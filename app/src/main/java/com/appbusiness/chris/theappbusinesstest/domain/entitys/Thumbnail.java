package com.appbusiness.chris.theappbusinesstest.domain.entitys;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chris on 12/08/2016.
 */
public class Thumbnail {

	@SerializedName("path")
	public String path;

	@SerializedName("extension")
	public String extension;

	public String getPath() {
		return path;
	}

	public String getExtension() {
		return extension;
	}
}
