package com.appbusiness.chris.theappbusinesstest.data.rest;

import com.appbusiness.chris.theappbusinesstest.domain.entitys.ComicDataWrapper;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;

/**
 * Created by Chris on 12/08/2016.
 */
public class RxCallAdapterErrorHandler implements rx.functions.Func1<Throwable, Observable> {

	private Retrofit mRetrofit;

	RxCallAdapterErrorHandler() {}

	/**
	 * This method must be called before {@link #call(Throwable)} is called, or else the response wont be able to be parsed
	 */
	void setRetrofit(Retrofit retrofit) {
		mRetrofit = retrofit;
	}

	@Override
	public Observable call(Throwable throwable) {
		ApiException apiException;

		//Standard error
		if (throwable instanceof HttpException) {
			HttpException httpException = (HttpException) throwable;
			Response response = httpException.response();

			Converter<ResponseBody, ComicDataWrapper> converter = mRetrofit.responseBodyConverter(ComicDataWrapper.class, new Annotation[0]);
			try {
				ComicDataWrapper apiResponse = converter.convert(response.errorBody());
				apiException = ApiException.createFromIntactResponse(apiResponse, response.code());
			} catch (IOException |JsonSyntaxException e) {
				e.printStackTrace();
				//Failed conversion

				apiException = ApiException.createFromGenericError(ApiException.OTHER_ERROR);
				apiException.initCause(e);
			}

		}
		//No Internet / Network error
		else if (throwable instanceof UnknownHostException || throwable instanceof SocketTimeoutException || throwable instanceof IOException) {
			apiException = ApiException.createFromNetworkError();
		}
		//Who knows
		else {
			apiException = ApiException.createFromGenericError(ApiException.UNKNOWN_ERROR);
		}

		return Observable.error(apiException);
	}
}
