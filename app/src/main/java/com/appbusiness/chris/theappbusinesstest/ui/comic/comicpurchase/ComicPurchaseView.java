package com.appbusiness.chris.theappbusinesstest.ui.comic.comicpurchase;

import com.appbusiness.chris.theappbusinesstest.ui.base.MvpView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.Observable;

/**
 * Created by Chris on 12/08/2016.
 */
public interface ComicPurchaseView extends MvpView {

	Observable<TextViewTextChangeEvent> getBudgetEntryObservable();

	void setTextAndSelection(String newString, int length);

	void setAmount(Integer amountOfComicsThatCanBeBrought);
}
