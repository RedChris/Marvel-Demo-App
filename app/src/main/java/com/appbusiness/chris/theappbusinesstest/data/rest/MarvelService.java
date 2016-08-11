package com.appbusiness.chris.theappbusinesstest.data.rest;

import com.appbusiness.chris.theappbusinesstest.domain.entitys.Comic;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Chris on 11/08/2016.
 */


public interface MarvelService {

	String ENDPOINT = "http://gateway.marvel.com/";

	@GET("v1/public/comics")
	Observable<List<Comic>> getComics(@Query("apikey") String apikey);

	/******** Factory class that sets up a new ribot services *******/
	class Factory {

		public static MarvelService makeMarvelService() {

			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(MarvelService.ENDPOINT)
					.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
					.build();
			return retrofit.create(MarvelService.class);
		}

	}
}