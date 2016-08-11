package com.appbusiness.chris.theappbusinesstest.ui.base;

import android.support.annotation.CallSuper;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Chris on 11/08/2016.
 */

public class  BasePresenter<T extends MvpView> implements Presenter<T> {

	private T mView;
	private CompositeSubscription mSubscription;

	public BasePresenter() {
		mSubscription = new CompositeSubscription();
	}

	@Override
	@CallSuper
	public void attachView(T mvpView) {
		mView = mvpView;
	}

	public T getView() {
		return mView;
	}

	@Override
	@CallSuper
	public void detachView() {
		mView = null;
	}

	protected CompositeSubscription getSubscription() {
		return mSubscription;
	}

	/**
	 * Calling un-subscribe on a compositeSubscription will render it un-usable, as it will automatically
	 * ub-subscribe aanything added to it afterwards. This method will call un-subscribe the compositeSubscription
	 * and re-create the object.
	 */
	protected void recreateSubscription() {
		mSubscription.unsubscribe();
		mSubscription = new CompositeSubscription();
	}
}
