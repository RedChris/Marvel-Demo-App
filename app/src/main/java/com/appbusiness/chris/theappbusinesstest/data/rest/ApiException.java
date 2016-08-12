package com.appbusiness.chris.theappbusinesstest.data.rest;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import com.appbusiness.chris.theappbusinesstest.classes.StringResHolder;
import com.appbusiness.chris.theappbusinesstest.classes.UtilErrorString;
import com.appbusiness.chris.theappbusinesstest.domain.entitys.ComicDataWrapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Chris on 12/08/2016.
 */
public class ApiException extends RuntimeException {

	private final Integer BAD_REQUEST = 400;

	@IntDef({NETWORK_ERROR, UNSUCCESSFULL_ERROR, BAD_REQUEST_ERROR, UNKNOWN_ERROR, OTHER_ERROR})
	@Retention(RetentionPolicy.SOURCE)
	public @interface ERROR_TYPE {}

	public static final int NETWORK_ERROR = 0;
	public static final int UNSUCCESSFULL_ERROR = 1;
	public static final int BAD_REQUEST_ERROR = 2;
	public static final int OTHER_ERROR = 3;
	public static final int UNKNOWN_ERROR = 4;

	private final Integer errorType;
	private final Pair<StringResHolder, StringResHolder> displayStringPair;
	private final ComicDataWrapper response;
	private final Integer errorCode;


	public static ApiException createFromGenericError(@NonNull @ERROR_TYPE Integer errorType) {
		return new ApiException(errorType);
	}

	public static ApiException createFromNetworkError() {
		return new ApiException(NETWORK_ERROR, UtilErrorString.GENERIC_NETWORK_ERROR_STRING_PAIR);
	}

	public static ApiException createFromIntactResponse(@NonNull ComicDataWrapper response, Integer errorCode) {
		return new ApiException(BAD_REQUEST_ERROR,
				UtilErrorString.GENERIC_NETWORK_ERROR_STRING_PAIR,
				response,
				errorCode);
	}

	private ApiException(@NonNull @ERROR_TYPE Integer errorType) {
		this.errorType = errorType;
		this.displayStringPair = UtilErrorString.GENERIC_ERROR_STRING_PAIR;
		this.response = null;
		this.errorCode = null;
	}

	private ApiException(@NonNull @ERROR_TYPE Integer errorType, @NonNull Pair<StringResHolder, StringResHolder> displayStringPair) {
		this.errorType = errorType;
		this.displayStringPair = displayStringPair;
		this.response = null;
		this.errorCode = null;
	}

	private ApiException(@NonNull Integer errorCode,
						@NonNull Pair<StringResHolder, StringResHolder> displayStringPair,
						@NonNull ComicDataWrapper response,
						@NonNull @ERROR_TYPE Integer errorType) {

		this.errorType = errorType;
		this.displayStringPair = displayStringPair;
		this.response = response;
		this.errorCode = errorCode;
	}

	private ApiException(@NonNull ApiException apiException, @NonNull Pair<StringResHolder, StringResHolder> displayStringPair) {
		this.errorType = apiException.getErrorType();
		this.displayStringPair = displayStringPair;
		this.response = apiException.getResponse();
		this.errorCode = apiException.getErrorCode();
	}

	@Nullable
	public Integer getErrorCode() {
		return errorCode;
	}

	public Pair<StringResHolder, StringResHolder> getDisplayStringPair() {
		return displayStringPair;
	}

	@Nullable
	public ComicDataWrapper getResponse() {
		return response;
	}

	@ERROR_TYPE
	public Integer getErrorType() {
		return errorType;
	}

	public ApiException withDisplayStringPair(@NonNull Pair<StringResHolder, StringResHolder> displayStringPair) {
		return new ApiException(this, displayStringPair);
	}
}
