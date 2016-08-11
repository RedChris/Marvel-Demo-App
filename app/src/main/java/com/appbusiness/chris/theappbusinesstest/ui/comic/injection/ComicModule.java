package com.appbusiness.chris.theappbusinesstest.ui.comic.injection;

import com.appbusiness.chris.theappbusinesstest.data.ComicManager;
import com.appbusiness.chris.theappbusinesstest.data.rest.MarvelService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Chris on 11/08/2016.
 */

@Module
public class ComicModule {

	@Provides
	@ComicScope
	ComicManager provideRepositoriesManager(MarvelService marvelService) {
		return new ComicManager(marvelService);
	}
}
