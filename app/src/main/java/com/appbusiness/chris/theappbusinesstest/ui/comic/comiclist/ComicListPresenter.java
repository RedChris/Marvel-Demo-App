package com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist;

import android.os.Bundle;

import com.appbusiness.chris.theappbusinesstest.R;
import com.appbusiness.chris.theappbusinesstest.data.ComicManager;
import com.appbusiness.chris.theappbusinesstest.data.rest.ApiException;
import com.appbusiness.chris.theappbusinesstest.data.rest.MarvelApiSubscriber;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.Comic;
import com.appbusiness.chris.theappbusinesstest.ui.base.BasePresenter;
import com.appbusiness.chris.theappbusinesstest.ui.comic.models.ComicModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by Chris on 11/08/2016.
 */
public class ComicListPresenter extends BasePresenter<ComicListView> {

	private ComicManager mComicManager;

	@Inject
	public ComicListPresenter(ComicManager comicManager) {
		mComicManager = comicManager;
	}

	private void loadComics() {
		mView.hideRetryButton();
		mView.showProgressView();

		getSubscription().add(mComicManager.getCurrentAndFreshComics()
				.map(comics -> {
					List<ComicModel> comicModelList = new ArrayList<>();

					for (Comic comic : comics) {
						comicModelList.add(new ComicModel(comic));
					}
					return comicModelList;
				})
				.doOnNext(new Action1<List<ComicModel>>() {
					@Override
					public void call(List<ComicModel> comicModels) {
						if (comicModels != null && !comicModels.isEmpty()) {
							int totalPageCount = 0;
							for (ComicModel comicModel : comicModels) {
								Integer numberOfPages = comicModel.getPageCount();
								if (numberOfPages != null) {
									totalPageCount += numberOfPages;
								}
							}
							mView.setTotalNumberOfPages(totalPageCount);
						}
					}
				})
				.subscribe(new MarvelApiSubscriber<List<ComicModel>>() {

					@Override
					public void onNext(List<ComicModel> comics) {
						mView.updateListView(comics);
						if (comics.isEmpty()) {
							mView.showEmptyView();
							mView.showRetryButton();
						} else {
							mView.hideRetryButton();
							mView.hideEmptyView();
						}
						mView.hideProgressView();
					}


					@Override
					public void onApiError(ApiException e) {
						mView.hideProgressView();
						mView.showRetryButton();
						mView.showErrorMessage(e.getDisplayStringPair().first, e.getDisplayStringPair().second);
					}
				}));
	}

	public void userWantsToViewComic(Integer id) {
		Bundle bundle = new Bundle();
		bundle.putInt(ComicModel.KEY_COMIC_ID, id);
		mView.moveToComicDetailPage(bundle);
	}

	public void userWantsToReloadComics() {
		loadComics();
	}

	@Override
	public void attachView(ComicListView mvpView) {
		super.attachView(mvpView);
		loadComics();
	}

	@Override
	public void detachView() {
		super.detachView();
		getSubscription().unsubscribe();
	}
}
