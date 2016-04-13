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

public class ProfileCreator {
	public static void generateProfile(String filesPath) {
		GameLauncher.bar.setValue(0);
		GameLauncher.text.setText(GameLauncher.text.getText() + "\nCreating profile files...");
		File saveFile = new File(filesPath + "/saves");
		if(!saveFile.exists()) {
			saveFile.mkdir();
			try {
				URL website = new URL("http://www.estrelsteel.com/games/" + GameLauncher.title.toLowerCase() + "/download/profile0.cu1");
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				@SuppressWarnings("resource")
				FileOutputStream fos = new FileOutputStream(filesPath + "/saves/profile0.cu1");
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
			try {
				ArrayList<String> lines = FileHandler.read(filesPath + "/saves/profile0.cu1");
				FileWriter fw;
				BufferedWriter bw;
				for(int p = 1; p < 4; p++) {
					GameLauncher.bar.setValue(GameLauncher.bar.getValue() + (100 / 3));
					fw = new FileWriter(filesPath + "/saves/profile" + p + ".cu1");
					bw = new BufferedWriter(fw);
					for(String l : lines) {
						bw.write(l + "\n");
					}
					bw.flush();
					bw.close();
				}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		GameLauncher.text.setText(GameLauncher.text.getText() + "\nProfile creation complete...");
	}
}
