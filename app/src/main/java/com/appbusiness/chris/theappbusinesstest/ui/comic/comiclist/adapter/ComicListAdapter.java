package com.appbusiness.chris.theappbusinesstest.ui.comic.comiclist.adapter;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusiness.chris.theappbusinesstest.R;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.Comic;
import com.appbusiness.chris.theappbusinesstest.ui.comic.models.ComicModel;
import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

/**
 * Created by Chris on 11/08/2016.
 */
public class ComicListAdapter extends RecyclerView.Adapter<ComicViewHolder> {

	private Context mContext;
	private List<ComicModel> mComicItems;
	private OnComicClickedListener mOnComicClickedListener;

	public ComicListAdapter(Context context) {
		mComicItems = Collections.emptyList();
		mContext = context;
	}

	@Override
	public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.item_comic, parent, false);
		return new ComicViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ComicViewHolder holder, int position) {
		final ComicModel comicModel = mComicItems.get(position);

		String thumbnailUrl = comicModel.getThumbnailUrl();
		if (thumbnailUrl != null) {
			Glide
					.with(mContext)
					.load(thumbnailUrl)
					.centerCrop()
					.crossFade()
					.into(holder.mThumbnail);
		} else {
			Glide.clear(holder.mThumbnail);
			holder.mThumbnail.setImageDrawable(null);
		}


		holder.mTitle.setText(comicModel.getTitle());
		holder.mIssueNumber.setText(String.format(mContext.getString(R.string.comic_list_item_issue), comicModel.getIssueNumber()));

		holder.itemView.setOnClickListener(view -> {
			if (mOnComicClickedListener != null) {
				mOnComicClickedListener.onComicClicked(comicModel.getId());
			}
		});
	}

	@Override
	public int getItemCount() {
		return (mComicItems == null)? 0 : mComicItems.size();
	}

	public void setOnComicClickedListener(@Nullable OnComicClickedListener onComicClickedListener) {
		this.mOnComicClickedListener = onComicClickedListener;

	}

	public void setComicListItems(List<ComicModel> items) {
		if (mComicItems == null) {
			mComicItems = Collections.emptyList();
		}

		mComicItems = items;
		notifyDataSetChanged();
	}


	public interface OnComicClickedListener {

		public void onComicClicked(Integer id);
	}
}
