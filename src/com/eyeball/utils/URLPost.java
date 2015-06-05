package com.eyeball.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;

public class URLPost {

	private static final String ENCODING = "UTF-8";
	private HashMap<String, String> post;

	public URLPost() {
		post = new HashMap<String, String>();
	}

	public void put(String key, String value) {
		try {
			this.post.put(URLEncoder.encode(key, ENCODING),
					URLEncoder.encode(value, ENCODING));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getPost() {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> entry : post.entrySet()) {
			builder.append(entry.getKey()).append('=').append(entry.getValue())
					.append('&');
		}
		builder.deleteCharAt(builder.length() - 1);
		return new String(builder);
	}

}