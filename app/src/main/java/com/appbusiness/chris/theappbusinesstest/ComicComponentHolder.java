package com.appbusiness.chris.theappbusinesstest;

import com.appbusiness.chris.theappbusinesstest.injection.ApplicationComponent;
import com.appbusiness.chris.theappbusinesstest.ui.comic.injection.ComicComponent;
import com.appbusiness.chris.theappbusinesstest.ui.comic.injection.ComicModule;

/**
 * Created by Chris on 11/08/2016.
 */
public class ComicComponentHolder {

	private final ApplicationComponent mApplicationComponent;
	private ComicComponent mComicComponent;

	public ComicComponentHolder(ApplicationComponent applicationComponent) {
		mApplicationComponent = applicationComponent;
	}

	public ComicComponent createComicComponent() {
		mComicComponent = mApplicationComponent.plus(new ComicModule());
		return mComicComponent;
	}

	public ComicComponent getComicComponent() {
		if (mComicComponent == null) {
			createComicComponent();
		}
		return mComicComponent;
	}

	public void releaseComicComponent() {
		mComicComponent = null;
	}

}
