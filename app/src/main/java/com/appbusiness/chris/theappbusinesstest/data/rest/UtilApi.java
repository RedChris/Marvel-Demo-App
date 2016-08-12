package com.appbusiness.chris.theappbusinesstest.data.rest;

import com.appbusiness.chris.theappbusinesstest.domain.entitys.ComicDataWrapper;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Chris on 11/08/2016.
 */
public class UtilApi {

	public static rx.Observable.Transformer<ComicDataWrapper, ComicDataWrapper> applySchedulersAndCheckForError() {
		return observable -> observable
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnNext((Action1<ComicDataWrapper>) response -> {
					if (!isMarvelServiceResponseSuccessful(response)) {
						throw ApiException.createFromIntactResponse(response, response.getCode());
					}
				});
	}

	public static boolean isMarvelServiceResponseSuccessful(ComicDataWrapper response) {
		Integer statusCode = response.getCode();
		return (statusCode != null  && statusCode == 200);
	}
}
