package com.appbusiness.chris.theappbusinesstest.injection;

import android.app.Application;
import android.content.Context;

import com.appbusiness.chris.theappbusinesstest.data.rest.MarvelService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Chris on 11/08/2016.
 */

@Module
public class ApplicationModule {

	protected final Application mApplication;


	public ApplicationModule(Application application) {
		mApplication = application;
	}


	@Provides
	Application provideApplication() {
		return mApplication;
	}


	@Provides
	//@ApplicationContext
	Context provideContext() {
		return mApplication;
	}

	@Provides
	@Singleton
	MarvelService provideApiManager() {
		return MarvelService.Factory.makeMarvelService();
	}
}
