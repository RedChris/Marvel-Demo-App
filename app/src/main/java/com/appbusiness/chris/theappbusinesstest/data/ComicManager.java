package com.appbusiness.chris.theappbusinesstest.data;

import android.util.Log;

import com.appbusiness.chris.theappbusinesstest.data.rest.MarvelService;

import com.appbusiness.chris.theappbusinesstest.domain.entitys.ComicDataWrapper;
import com.appbusiness.chris.theappbusinesstest.ui.comic.comicdetail.ComicDetailView;
import com.appbusiness.chris.theappbusinesstest.ui.comic.models.ComicModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Chris on 11/08/2016.
 */
public class ComicManager {

	private final MarvelService mMarvelService;
	private List<ComicModel> mComicModels = new ArrayList<>();

	public ComicManager(MarvelService marvelService) {
		mMarvelService = marvelService;
	}


	public void getCurrentAndFreshComics() {// cache conact local fresh
		mMarvelService.getComics()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<ComicDataWrapper>() {
			@Override
			public void onCompleted() {
				Log.i("Test", "complete");
			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
				Log.i("Test", "error");
			}

			@Override
			public void onNext(ComicDataWrapper s) {
				Log.i("Test", "next " + s.getData().getComics().get(1).getTitle());
			}
		});
	}
}
