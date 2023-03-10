package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {


	public static void main (String[] arg) {
		int width = 1920;
		short height = 1100;
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowSizeLimits(width,height,width,height);

		String beginning = "Beginning";
		config.setTitle(beginning);
		new Lwjgl3Application(new MyGdxGame2(), config);
	}
}
