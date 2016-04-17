package com.estrelsteel.launcher;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;

public class GameLauncher {
	public static final String title = "ShapeCrafter_estrelsteel";
	public static JTextPane text;
	public static JProgressBar bar;
	private static String version = "v2.0a";
	
	public static void main(String[] args) {
		String filesPath = "";
		if(System.getProperty("os.name").startsWith("Windows")) {
			filesPath = System.getProperty("user.home") + "/AppData/Roaming/" + title;
		}
		else if(System.getProperty("os.name").startsWith("Mac")) {
			filesPath = System.getProperty("user.home") + "/Library/Application Support/" + title;
		}
		else if(System.getProperty("os.name").startsWith("Linux")) {
			filesPath = System.getProperty("user.home") + "/" + title;
		}
		File file = new File(filesPath + "");
		if(!file.exists()) {
			file.mkdir();
		}
		bar = new JProgressBar(0, 100);
		bar.setValue(0);
		bar.setIndeterminate(false);
		bar.setVisible(true);
		bar.setSize(400, 40);
		
		text = new JTextPane();
		text.setText(title + " Launcher " + version + "\n\n\nChecking files...");
		text.setVisible(true);
		text.setSize(300, 300);
		text.setEditable(false);
		
		JFrame frame = new JFrame(title + " Launcher");
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(text, BorderLayout.NORTH);
		frame.add(bar, BorderLayout.SOUTH);
		
		frame.setSize(800, 500);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		ProfileCreator.generateProfile(filesPath);
		text.setText(GameLauncher.text.getText() + "\nChecking for Update...");
		MetaCreator.generateMeta(filesPath);
		text.setText(GameLauncher.text.getText() + "\n\nLaunching...");
		try {
			Desktop.getDesktop().open(new File(filesPath + "/assets/" + title + ".jar"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
