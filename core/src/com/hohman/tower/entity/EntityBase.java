package com.hohman.tower.entity;

import java.awt.geom.Rectangle2D;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hohman.tower.map.TDMap;

public abstract class EntityBase {

	protected float stateTime = 0;
	public Vector2 pos;
	public Vector2 vel = new Vector2(0,0);
	protected Rectangle2D.Float boundingBox = new Rectangle2D.Float();
	private Vector2 size;

	public EntityBase(Vector2 pos, Vector2 vel, Vector2 size) {
		this.pos = new Vector2(pos);
		this.vel = new Vector2(vel);
		this.size = size;
		updateBoundingBox();
	}
	
	public boolean update(TDMap map, float delta) {
		stateTime = stateTime + delta;
		pos.x += vel.x*delta;
		pos.y += vel.y*delta;

		updateBoundingBox();
		
		return false; // don't destroy me...
	}

	// if you override the update method, make sure to call this!
	protected void updateBoundingBox() {
		boundingBox.setRect(pos.x, pos.y, size.x, size.y);		
	}
	
	public abstract void render(SpriteBatch batch);
	
	public Rectangle2D.Float getBoundingBox() {
		return boundingBox;
	}
	
}
