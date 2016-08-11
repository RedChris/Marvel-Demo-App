package com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist;

import com.appbusiness.chris.theappbusinesstest.data.ComicManager;
import com.appbusiness.chris.theappbusinesstest.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Chris on 11/08/2016.
 */
public class ComicListPresenter extends BasePresenter<ComicListView> {

	private ComicManager mComicManager;

	@Inject
	public ComicListPresenter(ComicManager comicManager) {
		mComicManager = comicManager;

		getComics();
	}

	private void getComics() {
		mComicManager.getCurrentAndFreshComics();
	}

	@Override
	public void attachView(ComicListView mvpView) {
		super.attachView(mvpView);
	}

	@Override
	public void detachView() {
		super.detachView();
		getSubscription().unsubscribe();
	}
}
