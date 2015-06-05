package com.eyeball.pastebin.api;

/**
 * 
 * The contents of a paste
 * 
 * @author eyeballcode
 *
 */
public class PastebinPasteContent {

	private StringBuilder text = new StringBuilder();

	/**
	 * 
	 * Adds a line of text to the paste.
	 * 
	 * @param text
	 */
	public void append(Object text) {
		if (this.text.length() > 0)
			this.text.append("\n");
		this.text.append(text);
	}

	/**
	 * 
	 * Get the contents of the post.
	 * 
	 */
	@Override
	public String toString() {
		return text.toString();
	}

	/**
	 * 
	 * Sets the contents of the paste.
	 * 
	 * <br>
	 * Use this method if you prefer to work with {@link StringBuilder}.
	 * 
	 * @param content
	 *            The new contents
	 */
	public void setContent(StringBuilder content) {
		text = content;
	}

}
