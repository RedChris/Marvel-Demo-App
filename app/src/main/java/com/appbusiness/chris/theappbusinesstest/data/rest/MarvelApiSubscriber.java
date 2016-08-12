package com.appbusiness.chris.theappbusinesstest.data.rest;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Chris on 12/08/2016.
 */

public abstract class MarvelApiSubscriber <T> extends Subscriber<T> implements Observer<T>, Subscription {

	@Override
	public final void onError(Throwable e) {
		if (e instanceof ApiException) {
			onApiError((ApiException) e);
		} else {
			onThrowableError(e);
		}
	}

	@Override
	public void onCompleted() {}

	public abstract void onApiError(ApiException e);

	public void onThrowableError(Throwable e) {
		e.printStackTrace();
	}
}