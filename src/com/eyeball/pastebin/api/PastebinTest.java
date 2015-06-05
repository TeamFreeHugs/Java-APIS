package com.eyeball.pastebin.api;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.eyeball.pastebin.api.PastebinPaste.PastebinPasteExpiryTypes;

public class PastebinTest {
	static JFrame frame = new JFrame("Pastebin API GUI by Eyeballcode");
	static PastebinDeveloperKey key;
	static JPanel panel;
	static JTextField title = new JTextField();
	static JTextField devKey = new JTextField();

	static JTextField username = new JTextField();
	static JPasswordField pass = new JPasswordField();
	static JTextField lang = new JTextField();
	static JTextArea contents = new JTextArea();
	static JButton paste = new JButton("Paste!");

	public static void main(String[] args) throws IOException,
			PastebinBadAPIRequestException {
		System.out.println("Hello World!");
		paste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (devKey.getText().isEmpty() | contents.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Dev key or Paste Contents cannot be empty!");
					return;
					}
				PastebinDeveloperKey key = new PastebinDeveloperKey(devKey
						.getText());
				PastebinPasteContent content = new PastebinPasteContent();
				for (String line : contents.getText().split("\n")) {
					content.append(line);
				}
				PastebinPaste paste = new PastebinPaste(title.getText(), lang
						.getText(), PastebinPasteExpiryTypes.NEVER, key);
				PastebinUser user = null;
				try {
					if (!username.getText().isEmpty()
							&& !String.copyValueOf(pass.getPassword())
									.isEmpty()) {
						user = new PastebinUser(key, username.getText(), String
								.copyValueOf(pass.getPassword()));
					}

					paste.setContent(content);

					PastebinPoster poster = new PastebinPoster(paste, user);
					poster.put();
					JFrame link = new JFrame("Success!");
					link.setLayout(new GridLayout(1, 2));
					link.add(new JLabel("Your link is: "));
					final JTextField li = new JTextField(poster.getResultURL());
					li.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							li.setSelectionStart(0);
							li.setSelectionEnd(li.getText().length());
						}
					});
					li.setEditable(false);
					link.add(li);
					link.setLocationRelativeTo(null);

					link.pack();
					link.setVisible(true);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null,
							"ERROR: " + exception.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel = new JPanel(new GridLayout(8, 2));
		panel.add(new JLabel("Title: "));
		panel.add(title);
		panel.add(new JLabel("Dev Key: "));
		panel.add(devKey);

		panel.add(new JLabel("Username: "));
		panel.add(username);
		panel.add(new JLabel("Password: "));
		panel.add(pass);
		panel.add(new JLabel("Language: "));
		panel.add(lang);
		contents.setPreferredSize(new Dimension(700, 300));
		frame.add(panel, BorderLayout.NORTH);
		frame.add(contents, BorderLayout.CENTER);
		frame.add(paste, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
}
