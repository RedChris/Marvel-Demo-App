package com.appbusiness.chris.theappbusinesstest.ui.comic.comicpurchase;

import android.support.annotation.VisibleForTesting;

import com.appbusiness.chris.theappbusinesstest.data.ComicManager;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.Comic;
import com.appbusiness.chris.theappbusinesstest.ui.base.BasePresenter;
import com.appbusiness.chris.theappbusinesstest.ui.comic.models.ComicModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;

/**
 * Created by Chris on 12/08/2016.
 */
public class ComicPurchasePresenter extends BasePresenter<ComicPurchaseView> {

	private ComicManager mComicManager;

	private String mCurrentFormattedBudget = "";

	@Inject
	public ComicPurchasePresenter(ComicManager comicManager) {
		mComicManager = comicManager;
	}

	@Override
	public void attachView(ComicPurchaseView mvpView) {
		super.attachView(mvpView);

		getSubscription().add(Observable.combineLatest(budgetEntryObservable(mvpView), getSortedPriceAmounts(), new Func2<Double, List<Double>, Integer>() {
			@Override
			public Integer call(Double budget, List<Double> prices) {
				if (prices.isEmpty()) {
					return 0;
				}

				for (int i = 0; i < prices.size(); i++) {
					budget -= prices.get(i);

					if (budget < 0) {
						return i;
					}
				}

				return prices.size();
			}
		})
		.observeOn(AndroidSchedulers.mainThread())
		.subscribeOn(AndroidSchedulers.mainThread())
		.subscribe(new Subscriber<Integer>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			@Override
			public void onNext(Integer amountOfComicsThatCanBeBrought) {
				mvpView.setAmount(amountOfComicsThatCanBeBrought);
			}
		}));
	}

	@VisibleForTesting
	protected Observable<Double> budgetEntryObservable(ComicPurchaseView mvpView) {
		return mvpView.getBudgetEntryObservable()
				.debounce(400, TimeUnit.MILLISECONDS)
				.map(textViewTextChangeEvent -> textViewTextChangeEvent.text().toString())
				.filter(string -> !string.equals(mCurrentFormattedBudget))
				.observeOn(AndroidSchedulers.mainThread())
				.map(s -> {
					String cleanString = s.replaceAll("[$.,]", "");

					double parsed = 0d;
					try {
						parsed = Double.parseDouble(cleanString);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}

					parsed /= 100;

					NumberFormat numberFormat = NumberFormat.getNumberInstance();
					numberFormat.setMinimumFractionDigits(2);
					String formatted = numberFormat.format((parsed));

					//String formatted = Double.valueOf(parsed).toString();


					mCurrentFormattedBudget = formatted;
					mvpView.setTextAndSelection(formatted, formatted.length());
					return parsed;
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(AndroidSchedulers.mainThread());
	}

	@VisibleForTesting
	protected Observable<List<Double>> getSortedPriceAmounts() {
		return mComicManager.getComicsFromMemory()
				.map(comics -> {
					List<ComicModel> models = new ArrayList<>();
					for (Comic comic : comics) {
						models.add(new ComicModel(comic));
					}
					return models;
				})
				.map(comics -> {
					List<Double> priceAmounts = new ArrayList<>();
					for (ComicModel comic : comics) {
						priceAmounts.add(comic.getPrice().getPrice());
					}
					Collections.sort(priceAmounts);
					return priceAmounts;
				}).cache();
	}

	@Override
	public void detachView() {
		super.detachView();
		getSubscription().unsubscribe();
	}
}
