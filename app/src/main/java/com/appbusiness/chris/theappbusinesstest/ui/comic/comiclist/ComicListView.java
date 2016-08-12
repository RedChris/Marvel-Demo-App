package com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist;

import android.os.Bundle;

import com.appbusiness.chris.theappbusinesstest.classes.StringResHolder;
import com.appbusiness.chris.theappbusinesstest.ui.base.MvpView;
import com.appbusiness.chris.theappbusinesstest.ui.comic.models.ComicModel;

import java.util.List;

/**
 * Created by Chris on 11/08/2016.
 */
public interface ComicListView extends MvpView {
	void hideRetryButton();

	void showProgressView();

	void updateListView(List<ComicModel> items);

	void hideProgressView();

	void showRetryButton();

	void showErrorMessage(StringResHolder title, StringResHolder message);

	void moveToComicDetailPage(Bundle bundle);

	void showEmptyView();

	void hideEmptyView();

	void setTotalNumberOfPages(int totalPageCount);
}
