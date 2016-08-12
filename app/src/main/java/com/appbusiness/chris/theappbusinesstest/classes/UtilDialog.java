package com.appbusiness.chris.theappbusinesstest.classes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import com.appbusiness.chris.theappbusinesstest.R;

/**
 * Created by Chris on 12/08/2016.
 */
public class UtilDialog {

	public static ProgressDialog createProgressDialog(Context context, @StringRes int title, @StringRes int message) {
		return createProgressDialog(context, context.getString(title), context.getString(message));
	}

	public static ProgressDialog createProgressDialog(Context context, String title, String message) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setTitle(title);
		progressDialog.setMessage(message);
		return progressDialog;
	}

	public static AlertDialog createMessageDialog(Context context, @StringRes int title, @StringRes int message, @Nullable DialogInterface.OnClickListener clickListener) {
		return createMessageDialog(context, context.getString(title), context.getString(message), clickListener);
	}

	public static AlertDialog createMessageDialog(Context context, String title, String message, @Nullable DialogInterface.OnClickListener clickListener) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
				.setTitle(title)
				.setMessage(message)
				.setNeutralButton(R.string.dialog_action_ok, clickListener);
		return alertDialog.create();
	}
}
