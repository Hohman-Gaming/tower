package com.hohman.stickwars.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.hohman.stickwars.screens.GameScreen;

public class InputHandler {

	protected TDMap map = null;
	protected boolean mouseWasUp = true;
	
	public InputHandler(TDMap map) {
		this.map = map;
	}
	
	public void handleInput(float delta) {

		Vector2 mouseCoords = new Vector2(Gdx.input.getX(), GameScreen.SCREEN_PIXELS_DY-Gdx.input.getY());

		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mouseWasUp){
			mouseWasUp = false;
//			map.attemptPlaceTower(mouseCoords);
	    }
		
		if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
			mouseWasUp = true;
		
	}
}
