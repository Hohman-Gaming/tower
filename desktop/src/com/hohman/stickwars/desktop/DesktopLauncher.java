package com.hohman.stickwars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hohman.stickwars.StickWars;
import com.hohman.stickwars.screens.BaseScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = BaseScreen.SCREEN_PIXELS_DX;
		config.height = BaseScreen.SCREEN_PIXELS_DY;
		new LwjglApplication(new StickWars(), config);
	}
}
