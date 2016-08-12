package com.appbusiness.chris.theappbusinesstest.ui.comic.comicdetail;


import com.appbusiness.chris.theappbusinesstest.R;
import com.appbusiness.chris.theappbusinesstest.classes.StringResHolder;
import com.appbusiness.chris.theappbusinesstest.data.ComicManager;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.CreatorSummary;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.Price;
import com.appbusiness.chris.theappbusinesstest.ui.base.BasePresenter;
import com.appbusiness.chris.theappbusinesstest.ui.comic.models.ComicModel;


import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Chris on 11/08/2016.
 */
public class ComicDetailPresenter extends BasePresenter<ComicDetailView> {

	private ComicManager mComicManager;

	@Inject
	public ComicDetailPresenter(ComicManager comicManager) {
		mComicManager = comicManager;
	}

	@Override
	public void attachView(ComicDetailView mvpView) {
		super.attachView(mvpView);
	}

	@Override
	public void detachView() {
		super.detachView();
		getSubscription().unsubscribe();
	}

	public void userWantsToLoadComic(Integer comicId) {
		mComicManager.getComicFomMemory(comicId)
				.map(ComicModel::new)
				.subscribe(new Subscriber<ComicModel>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {
						mView.showError(new StringResHolder(R.string.comic_detail_error_title),
								new StringResHolder(R.string.comic_detail_error_message));
					}

					@Override
					public void onNext(ComicModel comicModel) {
						setupUiWithComic(comicModel);
					}
				});
	}

	private void setupUiWithComic(ComicModel comicModel) {
		mView.setThumbnail(comicModel.getThumbnailUrl());
		mView.setTitle(comicModel.getTitle());
		mView.setDescription(comicModel.getDescription());
		mView.setPrice(convertPriceToDollarAmount(comicModel.getPrice()));
		mView.setPageCount(comicModel.getPageCount().toString());
		mView.setCreators(getCreatorsString(comicModel.getCreators()));
	}

	private String convertPriceToDollarAmount(Price price) {
		return "$" + price.getPrice();
	}

	private String getCreatorsString(List<CreatorSummary> creators) {
		StringBuilder stringBuilder = new StringBuilder();
		for (CreatorSummary creator : creators) {
			stringBuilder.append(String.format("%s - %s", creator.getName(), creator.getRole()))
			.append("\n");
		}
		return stringBuilder.toString();
	}
}
