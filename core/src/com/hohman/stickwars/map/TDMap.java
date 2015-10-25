package com.hohman.stickwars.map;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.hohman.stickwars.entity.EntityBase;
import com.hohman.stickwars.screens.BaseScreen;
import com.hohman.stickwars.util.RGB;

public abstract class TDMap  {

	public TiledMap tiledMap = null;
	protected List<EntityBase> entities = new ArrayList<EntityBase>();
	protected List<EntityBase> entitiesAddAtEndOfUpdate = new ArrayList<EntityBase>();
	protected float gametime = 0;
	protected boolean updating = false;

	// rendering stuff
	protected OrthographicCamera cam = new OrthographicCamera(BaseScreen.SCREEN_PIXELS_DX, BaseScreen.SCREEN_PIXELS_DY);
	protected SpriteBatch batch = new SpriteBatch(5460);
	
	protected abstract RGB getBackgroundColor();
	
	public TDMap() {
	}

	/*
	 * renders the map to the screen.
	 */
	public void render() {

		RGB bgColor = getBackgroundColor();
		
		// clear the screen to white
		Gdx.gl.glClearColor( bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

		cam.position.set(cam.viewportWidth/2, cam.viewportHeight/2, 0);  // centered on the viewport
		this.cam.update();

		// paint each entity
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		
		for(EntityBase entity : entities) {
			entity.render(batch);
		}
		
		batch.end();
	}

	/*
	 * On each game tick, this updates all the things on the map - their position, etc...
	 */
	public void update(float delta) {
		gametime += delta;
		updating = true;
		
		List<EntityBase> dead = new ArrayList<EntityBase>();
		
		for(EntityBase entity : entities) {
			if (entity.update(this, delta)) {
				dead.add(entity);
			}
		}
		
		entities.addAll(entitiesAddAtEndOfUpdate);
		entities.removeAll(dead);
		entitiesAddAtEndOfUpdate.clear();
		updating = false;
	}
	
	public void addEntity(EntityBase entity) {
		// if in middle of update loop, don't add it directly, postpone until end of updating...
		if (updating)
			entitiesAddAtEndOfUpdate.add(entity);
		else
			entities.add(entity);
	}

	public float getGametime() {
		return gametime;
	}

	/* **************************************************************************
	 * UTILIY functions
	   ************************************************************************** */
	public List<EntityBase> getEntities() {
		return entities;
	}
	
	public float getMapWidth() {
		return cam.viewportWidth;
	}

	public float getMapHeight() {
		return cam.viewportHeight;
	}

}
