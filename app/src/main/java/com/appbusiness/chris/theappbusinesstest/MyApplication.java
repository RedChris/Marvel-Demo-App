package com.appbusiness.chris.theappbusinesstest;

import android.app.Application;
import android.content.Context;

import com.appbusiness.chris.theappbusinesstest.injection.ApplicationComponent;
import com.appbusiness.chris.theappbusinesstest.injection.ApplicationModule;
import com.appbusiness.chris.theappbusinesstest.injection.DaggerApplicationComponent;

/**
 * Created by Chris on 11/08/2016.
 */
public class MyApplication extends Application {

	ApplicationComponent mApplicationComponent;
	private ComicComponentHolder mComicComponentHolder;

	@Override
	public void onCreate() {
		super.onCreate();
		initComponent();
		mComicComponentHolder = new ComicComponentHolder(mApplicationComponent);
	}

	public static MyApplication get(Context context) {
		return (MyApplication) context.getApplicationContext();
	}

	private void initComponent() {
		mApplicationComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();
	}

	public ApplicationComponent getComponent() {
		return mApplicationComponent;
	}

	public ComicComponentHolder getComicComponentHolder() {
		return mComicComponentHolder;
	}

	// Needed to replace the component with a test specific one
	public void setComponent(ApplicationComponent applicationComponent) {
		mApplicationComponent = applicationComponent;
	}

}
