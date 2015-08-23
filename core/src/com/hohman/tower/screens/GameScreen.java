package com.hohman.tower.screens;

import com.badlogic.gdx.Game;
import com.hohman.tower.map.TDInputHandler;
import com.hohman.tower.map.TDMap;
import com.hohman.tower.map.TDMapRenderer;
import com.hohman.tower.map.levels.TDMapLevel1;

public class GameScreen extends BaseScreen {

	protected TDMap map; 
	protected TDMapRenderer renderer;
	protected TDInputHandler inputHandler;
	
	public GameScreen (Game game) {
		super(game);
	}

	@Override
	public void show () {
		map = new TDMapLevel1();
		renderer = new TDMapRenderer(map);
		inputHandler = new TDInputHandler(map);
	}

	@Override
	public void render(float delta) {
		inputHandler.handleInput(delta);
		map.update(delta);
		renderer.render(map);
	}

}
