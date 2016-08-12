package com.appbusiness.chris.theappbusinesstest.ui.comic.comicpurchase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.appbusiness.chris.theappbusinesstest.MyApplication;
import com.appbusiness.chris.theappbusinesstest.R;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by Chris on 12/08/2016.
 */
public class ComicPurchaseActivity extends AppCompatActivity implements ComicPurchaseView {

	@Inject
	ComicPurchasePresenter mComicPurchasePresenter;

	@BindView(R.id.budget) EditText mBudget;
	@BindView(R.id.amount) TextView mAmount;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comic_purchase);

		MyApplication.get(this).getComicComponentHolder()
				.getComicComponent().inject(this);

		ButterKnife.bind(this);

		getSupportActionBar().setTitle(R.string.comic_purchase_title);

		mComicPurchasePresenter.attachView(this);
	}

	@Override
	public Observable<TextViewTextChangeEvent> getBudgetEntryObservable() {
		return RxTextView.textChangeEvents(mBudget);
	}

	@Override
	public void setTextAndSelection(String newString, int length) {
		mBudget.setText(newString);
		mBudget.setSelection(length);
	}

	@Override
	public void setAmount(Integer amountOfComicsThatCanBeBrought) {
		mAmount.setText("You can buy up to " + amountOfComicsThatCanBeBrought + " comics");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mComicPurchasePresenter.detachView();
	}
}
