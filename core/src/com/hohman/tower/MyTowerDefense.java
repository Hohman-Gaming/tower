package com.hohman.tower;

import com.badlogic.gdx.Game;
import com.hohman.tower.screens.TitleScreen;

public class MyTowerDefense extends Game {
	
	@Override
	public void create () {
		setScreen(new TitleScreen(this));
	}

}
