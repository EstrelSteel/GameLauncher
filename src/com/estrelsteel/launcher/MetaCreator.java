package com.estrelsteel.launcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

public class MetaCreator {
	public static void generateMeta(String filesPath) {
		GameLauncher.bar.setValue(0);
		File metaFile = new File(filesPath + "/meta");
		boolean fileExist = true;
		if(!metaFile.exists()) {
			metaFile.mkdir();
			fileExist = false;
		}
		metaFile = new File(filesPath + "/meta/metadata.cu1");
		if(!metaFile.exists()) {
			try {
				URL website = new URL("http://www.estrelsteel.com/games/" + GameLauncher.title.toLowerCase() + "/download/metadata.cu1");
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				@SuppressWarnings("resource")
				FileOutputStream fos = new FileOutputStream(filesPath + "/meta/metadata.cu1");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			}
			catch (MalformedURLException e) {
				e.printStackTrace();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			ArrayList<String> local = FileHandler.read(filesPath + "/meta/metadata.cu1");
			ArrayList<String> web = FileHandler.readURL("http://www.estrelsteel.com/games/" + GameLauncher.title.toLowerCase() + "/download/metadata.cu1");
			if(!local.get(0).trim().equalsIgnoreCase(web.get(0).trim()) || !fileExist) {
				FileWriter fw = new FileWriter(filesPath + "/meta/metadata.cu1");
				BufferedWriter bw = new BufferedWriter(fw);
				for(String l : web) {
					bw.write(l + "\n");
				}	
				bw.flush();
				bw.close();
				GameLauncher.text.setText(GameLauncher.text.getText() + "\nUpdate Found.\nUpdating...");
				AssetDownloader.download(filesPath, web.get(1).trim());
				ResourceDownloader.download(filesPath);
				GameLauncher.text.setText(GameLauncher.text.getText() + "\nUpdate Complete.");
			}
			else {
				GameLauncher.bar.setValue(100);
				GameLauncher.text.setText(GameLauncher.text.getText() + "\nNo Update Found...");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
