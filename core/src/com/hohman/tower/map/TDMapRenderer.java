package com.hohman.tower.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.hohman.tower.entity.EntityBase;

public class TDMapRenderer {

	protected OrthogonalTiledMapRenderer renderer = null;
	public OrthographicCamera cam = new OrthographicCamera(32, 24);  // 6 tiles on right of 32x24 screen used for scoreboard
	protected SpriteBatch batch = new SpriteBatch(5460);
	protected Texture scoreBoard = null; 
	
	public TDMapRenderer(TDMap map) {
		float unitScale = 1 / 32f;
		renderer = new OrthogonalTiledMapRenderer(map.tiledMap, unitScale);
		scoreBoard = new Texture(Gdx.files.internal("scoreboard.png"));
	}
	
	public void render(TDMap map) {
		this.cam.position.set(16, 12, 0);
		this.cam.update();

		renderer.setView(cam);

		renderer.render();


		batch.setProjectionMatrix(cam.combined);
		batch.begin();

		batch.draw(scoreBoard, 26, 0, 6, 24);

		for(EntityBase entity : map.entities) {
			entity.render(batch);
		}
		
		batch.end();
	}
	
}
