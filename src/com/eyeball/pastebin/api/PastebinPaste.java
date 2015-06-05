package com.eyeball.pastebin.api;

public class PastebinPaste {

	private PastebinPasteContent content;

	private final String name, lang;

	private int type;

	public static final int PUBLIC = 0;
	public static final int UNLISTED = 1;
	public static final int PRIVATE = 2;

	private PastebinDeveloperKey key;

	private PastebinPasteExpiryTypes expiry;

	/**
	 * 
	 * 
	 * Creates a new <code>PastebinPaste</code> object.
	 * 
	 * This class is used to represent the content of a paste
	 * 
	 * @param name
	 *            The name of the post.
	 * @param language
	 *            The language of the post. (Programming language, of course!)
	 * 
	 * @param expiry
	 *            When the post will expire
	 * @param key
	 *            The developer key
	 */
	public PastebinPaste(String name, String language,
			PastebinPasteExpiryTypes expiry, PastebinDeveloperKey key) {
		lang = language;
		this.name = name;
		this.key = key;
		this.expiry = expiry;
	}

	/**
	 * 
	 * Set the paste's contents
	 * 
	 * @see com.eyeball.pastebin.api.PastebinPasteContent
	 * 
	 * @param content
	 * 
	 */
	public void setContent(PastebinPasteContent content) {
		this.content = content;
	}

	/**
	 * 
	 * Set the visibility of the paste.
	 * 
	 * @param type
	 *            The visibility
	 */
	public void setVisiblity(int type) {
		this.type = type;
	}

	/**
	 * 
	 * Get the paste's title
	 * 
	 * @return The title
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * Get the contents of the paste
	 * 
	 * @return The contents, or null if not set
	 */
	public PastebinPasteContent getContent() {
		return content;
	}

	/**
	 * 
	 * Get the paste's language
	 * 
	 * @return
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * 
	 * Get the visibility of the paste
	 * 
	 * @return
	 */
	public int getVisiblity() {
		return type;
	}

	/**
	 * 
	 * Get the Developer Key
	 * 
	 * @return The key
	 */
	public PastebinDeveloperKey getKey() {
		return key;
	}

	/**
	 * 
	 * Get the paste's expiry
	 * 
	 * @return
	 */
	public PastebinPasteExpiryTypes getExpiry() {
		return expiry;
	}

	public enum PastebinPasteExpiryTypes {
		NEVER("N"), TENMINUTES("10M"), ONEHOUR("1H"), ONEDAY("1D"), ONEWEEK(
				"1W"), TWOWEEKS("2W"), ONEMONTH("1M");

		private String time;

		PastebinPasteExpiryTypes(String time) {
			this.time = time;
		}

		@Override
		public String toString() {
			return time;
		}
	}
}
