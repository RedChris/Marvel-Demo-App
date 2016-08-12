package com.appbusiness.chris.theappbusinesstest.data.rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by Chris on 12/08/2016.
 */

public class RxApiErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
	private final RxJavaCallAdapterFactory original;
	private final RxCallAdapterErrorHandler mErrorHandler;

	private RxApiErrorHandlingCallAdapterFactory(RxCallAdapterErrorHandler callAdapterErrorHandler) {
		mErrorHandler = callAdapterErrorHandler;
		original = RxJavaCallAdapterFactory.create();
	}

	public static CallAdapter.Factory create(RxCallAdapterErrorHandler callAdapterErrorHandler) {
		return new RxApiErrorHandlingCallAdapterFactory(callAdapterErrorHandler);
	}

	@Override
	public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
		return new RxCallAdapterWrapper(original.get(returnType, annotations, retrofit), mErrorHandler, retrofit);
	}

	private static class RxCallAdapterWrapper implements CallAdapter<Observable<?>> {
		private final CallAdapter<?> wrapped;
		private final RxCallAdapterErrorHandler errorHandler;

		RxCallAdapterWrapper(CallAdapter<?> wrapped, RxCallAdapterErrorHandler errorHandler, Retrofit retrofit) {
			this.wrapped = wrapped;
			this.errorHandler = errorHandler;
			errorHandler.setRetrofit(retrofit);
		}

		@Override
		public Type responseType() {
			return wrapped.responseType();
		}

		@Override
		@SuppressWarnings("unchecked")
		public <R> Observable<?> adapt(Call<R> call) {
			return ((Observable) wrapped.adapt(call)).onErrorResumeNext(errorHandler);
		}
	}
}