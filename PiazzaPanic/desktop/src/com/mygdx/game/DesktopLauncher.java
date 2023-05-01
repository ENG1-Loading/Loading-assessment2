package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	/*
	 * Gets the configuration for the game
	 * @param none
	 * @return the configuration for the game
	 */
	public static Lwjgl3ApplicationConfiguration getConfig() {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setResizable(true);
		config.setWindowedMode(1280, 720);
		config.setTitle("Piazza Panic");
		return config;
	}

	public static void main(String[] args) {
		if (StartOnFirstThreadHelper.startNewJvmIfRequired()) return;
		Lwjgl3ApplicationConfiguration config = getConfig();
		new Lwjgl3Application(new PiazzaPanic(), config);
	}
}
