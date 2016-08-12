package com.appbusiness.chris.theappbusinesstest.ui.comic.comicdetail;

import com.appbusiness.chris.theappbusinesstest.classes.StringResHolder;
import com.appbusiness.chris.theappbusinesstest.ui.base.MvpView;

/**
 * Created by Chris on 11/08/2016.
 */
public interface ComicDetailView extends MvpView {
	void showError(StringResHolder title, StringResHolder message);

	void setThumbnail(String thumbnailUrl);

	void setTitle(String title);

	void setDescription(String description);

	void setPrice(String priceAsDollarAmount);

	void setPageCount(String pageCount);

	void setCreators(String creatorsString);
}
