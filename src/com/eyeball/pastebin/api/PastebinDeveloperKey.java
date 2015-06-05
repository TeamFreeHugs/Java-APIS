package com.eyeball.pastebin.api;

public class PastebinDeveloperKey {

	private final String key;

	/**
	 * 
	 * Makes a new <code>PastebinDeveloperKey</code> object.
	 * 
	 * This class is used to store the developer key.
	 * 
	 * @param key
	 *            The developer key.
	 */
	public PastebinDeveloperKey(String key) {
		this.key = key;
	}

	/**
	 * 
	 * Get the key
	 * 
	 * @return The key
	 */
	public String getKey() {
		return key;
	}

}
