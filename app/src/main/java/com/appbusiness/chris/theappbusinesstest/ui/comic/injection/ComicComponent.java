package com.appbusiness.chris.theappbusinesstest.ui.comic.injection;

import com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist.ComicListActivity;

import dagger.Subcomponent;

/**
 * Created by Chris on 11/08/2016.
 */

@ComicScope
@Subcomponent(
		modules = {
				ComicModule.class
		}
)
public interface ComicComponent {


	void inject(ComicListActivity comicListActivity);
}
