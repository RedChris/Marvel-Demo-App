package com.appbusiness.chris.theappbusinesstest.data;

import com.appbusiness.chris.theappbusinesstest.data.rest.MarvelService;

import com.appbusiness.chris.theappbusinesstest.data.rest.UtilApi;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.Comic;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.ComicDataWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import rx.Observable;
import rx.functions.Func1;


/**
 * Created by Chris on 11/08/2016.
 */
public class ComicManager {

	private final MarvelService mMarvelService;
	private List<Comic> mComicModels = new ArrayList<>();

	public ComicManager(MarvelService marvelService) {
		mMarvelService = marvelService;
	}


	public Observable<List<Comic>> getCurrentAndFreshComics() {
		Observable<List<Comic>> localMemoryComicObservable = getComicsFromMemory()
				.filter(comics -> comics != null && !comics.isEmpty());

		return Observable.concat(localMemoryComicObservable, getFreshComics()).cache();
	}

	public Observable<List<Comic>> getComicsFromMemory() {
		return Observable.just(mComicModels);
	}

	public Observable<List<Comic>> getFreshComics() {
		return mMarvelService.getComics()
				.compose(UtilApi.applySchedulersAndCheckForError())
				.map((Func1<ComicDataWrapper, List<Comic>>) comicDataWrapper -> {
					if (comicDataWrapper.getData() != null) {
						return comicDataWrapper.getData().getComics();
					}
					return Collections.emptyList();
				})
				.doOnNext(comics -> mComicModels = comics);
	}

	public Observable<Comic> getComicFomMemory(Integer comicId) {
		return getComicsFromMemory()
				.map(comics -> {
					for (Comic comic : comics) {
						if (comic.getId().equals(comicId)) {
							return comic;
						}
					}
					throw new NoSuchElementException("No comic with id" + comicId + " is in memory");
				}).cache();
	}
}
