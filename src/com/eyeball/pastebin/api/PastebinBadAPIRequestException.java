package com.eyeball.pastebin.api;

public class PastebinBadAPIRequestException extends Exception {

	private static final long serialVersionUID = -4281149671052362691L;

	public PastebinBadAPIRequestException(String message) {
		super(message);
	}
}
