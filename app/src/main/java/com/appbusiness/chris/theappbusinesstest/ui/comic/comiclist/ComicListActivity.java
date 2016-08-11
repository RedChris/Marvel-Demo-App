package com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appbusiness.chris.theappbusinesstest.MyApplication;
import com.appbusiness.chris.theappbusinesstest.R;
import com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist.adapter.ComicListAdapter;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ComicListActivity extends AppCompatActivity implements ComicListView {

	@Inject
	ComicListPresenter mComicListPresenter;

	private ComicListAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comic_list);

		MyApplication.get(this).getComicComponentHolder()
				.getComicComponent().inject(this);

		ButterKnife.bind(this);

		initUi();
		mComicListPresenter.attachView(this);
	}

	private void initUi() {
		mAdapter = new ComicListAdapter();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mComicListPresenter.detachView();
	}
}
