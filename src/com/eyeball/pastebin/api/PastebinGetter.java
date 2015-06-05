package com.eyeball.pastebin.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PastebinGetter {

	public static final String RAWURL = "http://pastebin.com/raw.php";

	public static PastebinPasteContent getPostContent(String ID)
			throws IOException, PastebinBadAPIRequestException {

		if (ID == null)
			throw new PastebinBadAPIRequestException("post ID cannot be null");

		if (ID.equals(""))
			throw new PastebinBadAPIRequestException(
					"Bad API request, post ID cannot be blank");

		try {
			URL post = new URL(RAWURL + "?i=" + ID);

			URLConnection connection = post.openConnection();
			connection.setDoOutput(true);

			PastebinPasteContent text = new PastebinPasteContent();
			{
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				for (String line = reader.readLine(); line != null; line = reader
						.readLine()) {
					text.append(line);
				}
				reader.close();
			}
			return text;
		} catch (FileNotFoundException e) {
			throw new PastebinBadAPIRequestException(
					"Bad API request, no such paste");
		}

	}

}
