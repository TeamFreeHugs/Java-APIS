package com.eyeball.pastebin.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import com.eyeball.utils.URLPost;

public class PastebinPoster {

	private final PastebinPaste paste;

	public static final String POSTURL = "http://pastebin.com/api/api_post.php";

	private String resultURL;

	private final PastebinUser user;

	/**
	 * 
	 * Make a new instance of PastebinPoster
	 * 
	 * @param paste
	 *            The paste to post on Pastebin.com
	 * @param user
	 *            The user to post as. May be null if you want to post as a
	 *            guest
	 */
	public PastebinPoster(PastebinPaste paste, PastebinUser user) {
		this.paste = paste;
		this.user = user;
	}

	/**
	 * 
	 * Posts the paste to Pastebin.com
	 * 
	 * @throws IOException
	 *             If the connection to Pastebin.com could not be made.
	 * @throws PastebinBadAPIRequestException
	 *             If any one of the credentials are wrong.
	 */
	public void put() throws IOException, PastebinBadAPIRequestException {
		URLPost postContents = new URLPost();
		postContents.put("api_dev_key", paste.getKey().getKey());
		postContents.put("api_option", "paste");
		if (user != null)
			postContents.put("api_user_key", user.getUserKey());
		if (paste.getName() != null)
			postContents.put("api_paste_name", paste.getName());
		if (paste.getContent() == null)
			paste.setContent(new PastebinPasteContent());
		postContents.put("api_paste_code", paste.getContent().toString());
		if (paste.getExpiry() != null)
			postContents.put("api_paste_expiry_date", paste.getExpiry()
					.toString());
		else
			postContents.put("api_paste_expiry_date", "N");
		if (paste.getLang() != null)
			postContents.put("api_paste_format", paste.getLang());
		if (paste.getVisiblity() < 3 && paste.getVisiblity() >= 0) {
			postContents.put("api_paste_priavte", "" + paste.getVisiblity());
		}
		URL post = new URL(POSTURL);
		URLConnection connection = post.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);

		{
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());

			writer.write(postContents.getPost());
			writer.flush();
			writer.close();
		}
		StringBuilder text = new StringBuilder();
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			for (String line = reader.readLine(); line != null; line = reader
					.readLine()) {
				if (text.length() > 0) {
					text.append('\n');
				}
				text.append(line);
			}
			reader.close();
		}

		String result = text.toString();

		if (result.startsWith("http")) {
			this.resultURL = result;

		} else
			throw new PastebinBadAPIRequestException(result);
	}

	/**
	 * 
	 * Get the resulting URL for the paste, or null if not sucessful
	 * 
	 * @return
	 */
	public String getResultURL() {
		return resultURL;
	}

}
