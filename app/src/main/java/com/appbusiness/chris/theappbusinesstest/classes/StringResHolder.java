package com.appbusiness.chris.theappbusinesstest.classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * Created by Chris on 03/08/2016.
 */
public class StringResHolder {

	final private @StringRes int stringResource;
	final Object[] formatArguments;

	public StringResHolder(@StringRes int stringResource) {
		this.stringResource = stringResource;
		this.formatArguments = null;
	}

	public StringResHolder(int stringResource, Object... formatArguments) {
		this.stringResource = stringResource;
		this.formatArguments = formatArguments;
	}

	@StringRes
	public int getStringResource() {
		return stringResource;
	}

	public String getString(@NonNull Context context) {
		if (formatArguments != null) {
			return String.format(context.getString(stringResource), formatArguments);
		} else {
			return context.getString(stringResource);
		}
	}

	public Object[] getFormatArguments() {
		return formatArguments;
	}
}

