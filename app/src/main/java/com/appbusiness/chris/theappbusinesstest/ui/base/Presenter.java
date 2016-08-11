package com.appbusiness.chris.theappbusinesstest.ui.base;

/**
 * Created by Chris on 11/08/2016.
 */

public interface Presenter<V extends MvpView> {

	void attachView(V mvpView);

	void detachView();
}