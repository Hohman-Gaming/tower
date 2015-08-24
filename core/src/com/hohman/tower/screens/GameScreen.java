package com.hohman.tower.screens;

import com.badlogic.gdx.Game;
import com.hohman.tower.map.Scoreboard;
import com.hohman.tower.map.InputHandler;
import com.hohman.tower.map.TDMap;
import com.hohman.tower.map.levels.TDMapLevel1;

public class GameScreen extends BaseScreen {

	protected TDMap map;   // the current map...
	public static Scoreboard scoreboard = new Scoreboard();  // keeps track of score, inventory, etc...
	protected InputHandler inputHandler;
	
	public GameScreen (Game game) {
		super(game);
	}

	@Override
	public void show () {
		map = new TDMapLevel1();
		inputHandler = new InputHandler(map);
	}

	@Override
	public void render(float delta) {
		inputHandler.handleInput(delta);
		map.update(delta);
		map.render();
		scoreboard.render(map);
	}

}
