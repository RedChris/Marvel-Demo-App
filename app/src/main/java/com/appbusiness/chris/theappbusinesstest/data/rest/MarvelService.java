package com.appbusiness.chris.theappbusinesstest.data.rest;

import com.appbusiness.chris.theappbusinesstest.BuildConfig;
import com.appbusiness.chris.theappbusinesstest.data.rest.intercepters.MarvelAuthenticationInterceptor;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.ComicDataWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import rx.Observable;

/**
 * Created by Chris on 11/08/2016.
 */


public interface MarvelService {

	String ENDPOINT = "http://gateway.marvel.com/";

	@GET("v1/public/comics?limit=100")
	Observable<ComicDataWrapper> getComics();

	/******** Factory class that sets up a new Marvel service *******/
	class Factory {

		public static MarvelService makeMarvelService() {

			RxCallAdapterErrorHandler errorHandler = new RxCallAdapterErrorHandler();

			Gson gson = new GsonBuilder().create();

			HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
			logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
					: HttpLoggingInterceptor.Level.NONE);

			OkHttpClient okHttpClient = new OkHttpClient.Builder()
					.addInterceptor(new MarvelAuthenticationInterceptor())
					.addNetworkInterceptor(logging)
					.build();

			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(MarvelService.ENDPOINT)
					.client(okHttpClient)
					.addConverterFactory(GsonConverterFactory.create(gson))
					.addCallAdapterFactory(RxApiErrorHandlingCallAdapterFactory.create(errorHandler))
					.build();

			return retrofit.create(MarvelService.class);
		}

	}
}