package com.hohman.stickwars;

import com.badlogic.gdx.Game;
import com.hohman.stickwars.screens.TitleScreen;

public class StickWars extends Game {
	
	@Override
	public void create () {
		setScreen(new TitleScreen(this));
	}

}
