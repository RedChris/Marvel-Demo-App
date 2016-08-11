package com.appbusiness.chris.theappbusinesstest.classes;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by Chris on 03/08/2016.
 */
public class StringResHolder {

	final private @StringRes int stringResource;

	public StringResHolder(@StringRes int stringResource) {
		this.stringResource = stringResource;
	}

	@StringRes
	public int getStringResource() {
		return stringResource;
	}

	public String getString(Context context) {
		return context.getString(stringResource);
	}
}
