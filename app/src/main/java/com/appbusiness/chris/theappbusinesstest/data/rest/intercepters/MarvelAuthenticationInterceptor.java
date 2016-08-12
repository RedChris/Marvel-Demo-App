package com.appbusiness.chris.theappbusinesstest.data.rest.intercepters;

import com.appbusiness.chris.theappbusinesstest.BuildConfig;
import com.appbusiness.chris.theappbusinesstest.classes.UtilString;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Chris on 12/08/2016.
 */
public class MarvelAuthenticationInterceptor implements Interceptor {


	@Override
	public Response intercept(Chain chain) throws IOException {
		Request original = chain.request();
		HttpUrl originalHttpUrl = original.url();

		final Long timeStamp = System.currentTimeMillis();

		String unHashedString = timeStamp + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY;
		String hash = null;

		try {
			hash = UtilString.md5Hash(unHashedString);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			// If null hash is added, it will result in a 409 error, which can be dealt with higher up
		}

		HttpUrl url = originalHttpUrl.newBuilder()
				.addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
				.addQueryParameter("ts", timeStamp.toString())
				.addQueryParameter("hash", hash)
				.build();

		// Request customization: add request headers
		Request.Builder requestBuilder = original.newBuilder()
				.url(url);

		Request request = requestBuilder.build();
		return chain.proceed(request);
	}
}
