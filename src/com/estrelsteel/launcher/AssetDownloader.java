package com.estrelsteel.launcher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class AssetDownloader {
	public static void download(String filesPath, String name) {
		GameLauncher.bar.setValue(0);
		GameLauncher.text.setText(GameLauncher.text.getText() + "\nDownloading Game Files...");
		File file = new File(filesPath + "/assets");
		if(!file.exists()) {
			file.mkdir();
		}
		try {
			//JOptionPane.showMessageDialog(null, "Minotaur Game Updated!", "Minotaur Launcher", JOptionPane.PLAIN_MESSAGE);
			URL website = new URL("http://www.estrelsteel.com/games/" + GameLauncher.title.toLowerCase() + "/versions/" + name);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			GameLauncher.bar.setValue(50);
			@SuppressWarnings("resource")
			FileOutputStream fos = new FileOutputStream(filesPath + "/assets/" + GameLauncher.title + ".jar");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		GameLauncher.bar.setValue(100);
		GameLauncher.text.setText(GameLauncher.text.getText() + "\nGame Files Download Complete...");
		
	}
}
