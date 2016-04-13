package com.estrelsteel.launcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
	public static ArrayList<String> read(String path) throws IOException {
		FileReader fr = new FileReader(path);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> lines = new ArrayList<String>();
		String line = br.readLine();
		while(line != null) {
			lines.add(line);
			line = br.readLine();
		}
		return lines;
	}
	
	public static ArrayList<String> readURL(String path) throws IOException {
		URL url = new URL(path);
		@SuppressWarnings("resource")
		Scanner s = new Scanner(url.openStream());
		ArrayList<String> lines = new ArrayList<String>();
		String line = s.next();
		while(line != null) {
			lines.add(line);
			if(s.hasNext()) {
				line = s.next();
			}
			else {
				line = null;
			}
		}
		return lines;
	}
}
