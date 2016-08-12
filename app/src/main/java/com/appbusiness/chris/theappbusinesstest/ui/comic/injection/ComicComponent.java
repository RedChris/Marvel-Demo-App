package com.appbusiness.chris.theappbusinesstest.ui.comic.injection;

import com.appbusiness.chris.theappbusinesstest.ui.comic.comicdetail.ComicDetailActivity;
import com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist.ComicListActivity;
import com.appbusiness.chris.theappbusinesstest.ui.comic.comicpurchase.ComicPurchaseActivity;

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

	void inject(ComicDetailActivity comicDetailActivity);

	void inject(ComicPurchaseActivity comicPurchaseActivity);
}
