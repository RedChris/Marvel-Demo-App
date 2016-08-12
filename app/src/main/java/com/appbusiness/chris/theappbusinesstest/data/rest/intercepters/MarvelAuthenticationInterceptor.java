package com.appbusiness.chris.theappbusinesstest.data.rest.intercepters;

import java.io.IOException;

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

		HttpUrl url = originalHttpUrl.newBuilder()
				.addQueryParameter("apikey", "your-actual-api-key")
				.build();

		// Request customization: add request headers
		Request.Builder requestBuilder = original.newBuilder()
				.url(url);

		Request request = requestBuilder.build();
		return chain.proceed(request);
	}

	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
}
