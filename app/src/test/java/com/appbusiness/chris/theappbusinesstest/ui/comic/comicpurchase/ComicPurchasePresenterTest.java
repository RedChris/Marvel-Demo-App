package com.appbusiness.chris.theappbusinesstest.ui.comic.comicpurchase;



import com.appbusiness.chris.theappbusinesstest.data.ComicManager;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by Chris on 12/08/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class ComicPurchasePresenterTest {

	@Mock ComicPurchaseView mMockComicPurchaseView;
	@Mock ComicManager mMockComicManager;

	private ComicPurchasePresenter mSubject;

	@Before
	public void setUp() throws Exception {
		mSubject = new ComicPurchasePresenter(mMockComicManager);
		mSubject.attachView(mMockComicPurchaseView);
	}
}