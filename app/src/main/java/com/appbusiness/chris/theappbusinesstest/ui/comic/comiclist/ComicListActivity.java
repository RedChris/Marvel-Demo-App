package com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appbusiness.chris.theappbusinesstest.MyApplication;
import com.appbusiness.chris.theappbusinesstest.R;
import com.appbusiness.chris.theappbusinesstest.classes.StringResHolder;
import com.appbusiness.chris.theappbusinesstest.classes.UtilDialog;
import com.appbusiness.chris.theappbusinesstest.ui.comic.comicdetail.ComicDetailActivity;
import com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist.adapter.ComicListAdapter;
import com.appbusiness.chris.theappbusinesstest.ui.comic.comicpurchase.ComicPurchaseActivity;
import com.appbusiness.chris.theappbusinesstest.ui.comic.models.ComicModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicListActivity extends AppCompatActivity implements ComicListView {

	@Inject
	ComicListPresenter mComicListPresenter;

	private ComicListAdapter mAdapter;

	private ProgressDialog mProgressDialog;

	@BindView(R.id.comic_list) RecyclerView mComicListView;
	@BindView(R.id.empty_view) TextView mEmptyView;
	@BindView(R.id.retry_button) Button mRetryButton;
	@BindView(R.id.total_pages) TextView mTotalPages;
	@BindView(R.id.purchase_comic) Button mPurchaseButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comic_list);

		MyApplication.get(this).getComicComponentHolder()
				.getComicComponent().inject(this);

		ButterKnife.bind(this);

		getSupportActionBar().setTitle(R.string.comic_list_title);

		initUi();
		mComicListPresenter.attachView(this);
	}

	private void initUi() {
		mAdapter = new ComicListAdapter(this);

		mRetryButton.setOnClickListener(view -> mComicListPresenter.userWantsToReloadComics());
		mPurchaseButton.setOnClickListener(view -> mComicListPresenter.userWantsToGoToPurchaseComicPage());

		setupListView();
	}

	private void setupListView() {
		mAdapter.setOnComicClickedListener(id -> mComicListPresenter.userWantsToViewComic(id));

		mComicListView.setLayoutManager(new LinearLayoutManager(this));
		mComicListView.setAdapter(mAdapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mComicListPresenter.detachView();
	}

	@Override
	public void showRetryButton() {
		mRetryButton.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideRetryButton() {
		mRetryButton.setVisibility(View.GONE);
	}

	@Override
	public void showProgressView() {
		if (mProgressDialog == null) {
			mProgressDialog = UtilDialog.createProgressDialog(this,
					getString(R.string.comics_list_loading_comics_now),
					"");
		}
		mProgressDialog.show();
	}

	@Override
	public void hideProgressView() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}
	}

	@Override
	public void updateListView(List<ComicModel> items) {
		mAdapter.setComicListItems(items);
	}

	@Override
	public void showErrorMessage(StringResHolder title, StringResHolder message) {
		UtilDialog.createMessageDialog(this, title.getStringResource(), message.getStringResource(), null).show();
	}

	@Override
	public void moveToComicDetailPage(Bundle bundle) {
		Intent intent = new Intent(this, ComicDetailActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void showEmptyView() {
		mEmptyView.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideEmptyView() {
		mEmptyView.setVisibility(View.GONE);
	}

	@Override
	public void setTotalNumberOfPages(int totalPageCount) {
		mTotalPages.setText(String.format(getString(R.string.comic_list_pages_in_total), totalPageCount));
	}

	@Override
	public void goToPurchasePage() {
		startActivity(new Intent(this, ComicPurchaseActivity.class));
	}
}
