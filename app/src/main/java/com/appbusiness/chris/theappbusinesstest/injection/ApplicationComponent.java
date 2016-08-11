package com.appbusiness.chris.theappbusinesstest.injection;

import android.app.Application;
import android.content.Context;

import com.appbusiness.chris.theappbusinesstest.MyApplication;
import com.appbusiness.chris.theappbusinesstest.data.rest.MarvelService;
import com.appbusiness.chris.theappbusinesstest.ui.comic.injection.ComicComponent;
import com.appbusiness.chris.theappbusinesstest.ui.comic.injection.ComicModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Chris on 11/08/2016.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

	void inject(MyApplication myApplication);

	/*@ApplicationContext*/ Context context();
	Application application();
	MarvelService marvelService();

	ComicComponent plus(ComicModule userModule);
}
