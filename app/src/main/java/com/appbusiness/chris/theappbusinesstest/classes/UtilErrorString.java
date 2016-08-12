package com.appbusiness.chris.theappbusinesstest.classes;

import android.support.v4.util.Pair;

import com.appbusiness.chris.theappbusinesstest.R;


/**
 * Created by Chris on 03/08/2016.
 */
public class UtilErrorString {

	public static final Pair<StringResHolder, StringResHolder> GENERIC_NETWORK_ERROR_STRING_PAIR = new Pair<>(
			new StringResHolder(R.string.generic_network_error_title),
			new StringResHolder(R.string.generic_network_error_message));

	public static final Pair<StringResHolder, StringResHolder> GENERIC_ERROR_STRING_PAIR = new Pair<>(
			new StringResHolder(R.string.generic_error_title),
			new StringResHolder(R.string.generic_error_message));
}
