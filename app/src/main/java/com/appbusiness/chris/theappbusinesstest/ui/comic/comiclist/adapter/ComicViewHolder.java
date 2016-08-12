package com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusiness.chris.theappbusinesstest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chris on 11/08/2016.
 */
public class ComicViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.thumbnail) ImageView mThumbnail;
	@BindView(R.id.title_text) TextView mTitle;
	@BindView(R.id.issue_number_text) TextView mIssueNumber;

	public ComicViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}