package com.hohman.stickwars.screens;

import com.badlogic.gdx.Game;
import com.hohman.stickwars.map.InputHandler;
import com.hohman.stickwars.map.TDMap;
import com.hohman.stickwars.map.levels.TDMapRandom;

public class GameScreen extends BaseScreen {

	protected TDMap map;   // the current map...
	protected InputHandler inputHandler;
	
	public GameScreen (Game game) {
		super(game);
	}

	@Override
	public void show () {
		map = new TDMapRandom();
		inputHandler = new InputHandler(map);
	}

	@Override
	public void render(float delta) {
		inputHandler.handleInput(delta);
		map.update(delta);
		map.render();
	}

}
