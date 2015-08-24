package com.hohman.tower.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hohman.tower.MyTowerDefense;
import com.hohman.tower.screens.BaseScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = BaseScreen.SCREEN_PIXELS_DX;
		config.height = BaseScreen.SCREEN_PIXELS_DY;
		new LwjglApplication(new MyTowerDefense(), config);
	}
}
