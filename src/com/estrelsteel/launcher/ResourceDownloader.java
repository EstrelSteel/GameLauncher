package com.estrelsteel.launcher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

public class ResourceDownloader {
	public static void download(String filesPath) {
		GameLauncher.bar.setValue(0);
		GameLauncher.text.setText(GameLauncher.text.getText() + "\nDownloading Resources...");
		File file = new File(filesPath + "/assets");
		if(!file.exists()) {
			file.mkdir();
		}
		file = new File(filesPath + "/assets/res/");
		if(!file.exists()) {
			file.mkdir();
		}
		file = new File(filesPath + "/assets/res/img");
		if(!file.exists()) {
			file.mkdir();
		}
		file = new File(filesPath + "/assets/res/sfx");
		if(!file.exists()) {
			file.mkdir();
		}
		try {
			ArrayList<String> downLines = FileHandler.readURL("http://www.estrelsteel.com/games/" + GameLauncher.title.toLowerCase() + "/download/webLoc.cu1");
			ArrayList<String> saveLines = FileHandler.readURL("http://www.estrelsteel.com/games/" + GameLauncher.title.toLowerCase() +"/download/saveLoc.cu1");
			for(int i = 0; i < downLines.size(); i++) {
				URL website = new URL(downLines.get(i));
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				GameLauncher.bar.setValue((int) ((((double) i) / ((double) downLines.size())) * 100));
				@SuppressWarnings("resource")
				FileOutputStream fos = new FileOutputStream(filesPath + saveLines.get(i));
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		GameLauncher.bar.setValue(0);
		GameLauncher.text.setText(GameLauncher.text.getText() + "\nResources Download Complete...");
		
	}
}
