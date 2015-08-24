package com.hohman.tower.map;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hohman.tower.entity.towers.EntityBaseTower;
import com.hohman.tower.screens.GameScreen;

public class Scoreboard {

	public static final int SCOREBOARD_PIXELS_DX = 6*32;
	public static final int SCOREBOARD_PIXELS_DY = GameScreen.SCREEN_PIXELS_DY;
	
	protected Texture background = null; 
	protected OrthographicCamera cam = new OrthographicCamera();
	protected SpriteBatch batch = new SpriteBatch();
	
	public Scoreboard() {
		background = new Texture(Gdx.files.internal("scoreboard.png"));
		cam.setToOrtho(false, GameScreen.SCREEN_PIXELS_DX , GameScreen.SCREEN_PIXELS_DY);  // pixel perfect ortho camera
	}
	
	public void render(TDMap map) {
		
		// the camera position 
		cam.position.set(GameScreen.SCREEN_PIXELS_DX/2-TDMap.MAP_DX*TDMap.CELL_DX, GameScreen.SCREEN_PIXELS_DY/2,0);  // set the camera to the right of the main map...
			// 0,0 is the lower left corner of the scoreboard...
		this.cam.update();

		// paint each entity
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(background, 0,0, SCOREBOARD_PIXELS_DX, SCOREBOARD_PIXELS_DY);

		// draw the towers available...
		List<EntityBaseTower> towersAvailable = map.getTowersAvailable();
		int x = 13;
		int y = SCOREBOARD_PIXELS_DY-45;
		for(EntityBaseTower tower : towersAvailable) {
			TextureRegion tr = tower.getTextureRegion();
			batch.draw(tr, x, y);
			x += TDMap.CELL_DX;
			if (x > SCOREBOARD_PIXELS_DX-TDMap.CELL_DX) {
				x = 10;
				y += TDMap.CELL_DY;
			}
		}
		
		batch.end();
	}
}
