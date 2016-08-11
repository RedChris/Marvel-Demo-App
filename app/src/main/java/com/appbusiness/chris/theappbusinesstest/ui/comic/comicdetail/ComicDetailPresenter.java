package com.appbusiness.chris.theappbusinesstest.ui.comic.comicdetail;

import com.appbusiness.chris.theappbusinesstest.data.ComicManager;
import com.appbusiness.chris.theappbusinesstest.ui.base.BasePresenter;
import com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist.ComicListView;

import javax.inject.Inject;

/**
 * Created by Chris on 11/08/2016.
 */
public class ComicDetailPresenter  extends BasePresenter<ComicListView> {

	private ComicManager mComicManager;

	@Inject
	public ComicDetailPresenter(ComicManager comicManager) {
		mComicManager = comicManager;
	}

	@Override
	public void attachView(ComicListView mvpView) {
		super.attachView(mvpView);
	}

	@Override
	public void detachView() {
		super.detachView();
	}
}
