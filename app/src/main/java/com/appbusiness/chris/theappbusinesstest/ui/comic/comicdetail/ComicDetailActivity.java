package com.appbusiness.chris.theappbusinesstest.ui.comic.comicdetail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusiness.chris.theappbusinesstest.MyApplication;
import com.appbusiness.chris.theappbusinesstest.R;
import com.appbusiness.chris.theappbusinesstest.classes.StringResHolder;
import com.appbusiness.chris.theappbusinesstest.classes.UtilDialog;
import com.appbusiness.chris.theappbusinesstest.ui.comic.models.ComicModel;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chris on 11/08/2016.
 */
public class ComicDetailActivity extends AppCompatActivity implements ComicDetailView {

	@Inject
	ComicDetailPresenter mComicDetailPresenter;

	private ActionBar mActionBar;
	@BindView(R.id.thumbnail) ImageView mThumbnail;
	@BindView(R.id.description) TextView mDescriptionText;
	@BindView(R.id.price_text) TextView mPriceText;
	@BindView(R.id.page_count_text) TextView mPageCountText;
	@BindView(R.id.creators_text) TextView mCreatorsText;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comic_detail);

		MyApplication.get(this).getComicComponentHolder()
				.getComicComponent().inject(this);

		ButterKnife.bind(this);

		final Integer comicId = getIntent().getIntExtra(ComicModel.KEY_COMIC_ID, -1);
		if (comicId == -1) {
			AlertDialog noIdDialog = UtilDialog.createMessageDialog(this,
					getString(R.string.comic_detail_no_id_error_title),
					getString(R.string.comic_detail_no_id_error_message),
					(dialogInterface, i) -> {
						finish();
					});

			noIdDialog.setCancelable(false);
			noIdDialog.show();
		} else {
			mComicDetailPresenter.attachView(this);
			initUi();
			mComicDetailPresenter.userWantsToLoadComic(comicId);
		}
	}

	private void initUi() {
		mActionBar = getSupportActionBar();
	}

	@Override
	public void showError(StringResHolder title, StringResHolder message) {
		AlertDialog dialog = UtilDialog.createMessageDialog(this, title.getStringResource(), message.getStringResource(), null);
		dialog.setOnDismissListener(dialogInterface -> finish());
		dialog.show();
	}

	@Override
	public void setTitle(String title) {
		mActionBar.setTitle(title);
	}

	@Override
	public void setThumbnail(String thumbnailUrl) {
		Glide
				.with(this)
				.load(thumbnailUrl)
				.centerCrop()
				.crossFade()
				.into(mThumbnail);
	}

	@Override
	public void setDescription(String description) {
		mDescriptionText.setText(description);
	}

	@Override
	public void setPrice(String priceAsDollarAmount) {
		mPriceText.setText(priceAsDollarAmount);
	}

	@Override
	public void setPageCount(String pageCount) {
		mPageCountText.setText(pageCount);
	}

	@Override
	public void setCreators(String creatorsString) {
		mCreatorsText.setText(creatorsString);
	}
}
