package com.hohman.stickwars.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.hohman.stickwars.entity.effects.EntityBloodyDeath;
import com.hohman.stickwars.map.TDMap;

public class EntityEnemy extends EntityDirectionalSprite {

	protected float scalarVel=0;
	protected float hp, starthp;
	protected static ShapeRenderer shapeRenderer = new ShapeRenderer();
	protected boolean playedDeathAnim = false;
	
	public EntityEnemy(String textureFileName, int texturePixelWidth, int texturePixelHeight, float keyFrameTiming, int startX, int startY, int numFrames,
			Vector2 pos, Vector2 vel, float starthp) {
		super(textureFileName, texturePixelWidth, texturePixelHeight, keyFrameTiming, startX, startY, numFrames, pos, vel);
		
		scalarVel = Math.max(vel.x, vel.y);
		this.hp=starthp;
		this.starthp=starthp;
	}

	// returns true if we died because of the damage
	public boolean processDamage(float damage) {
		hp -= damage;
		if (hp < 0)
			hp = 0;
		
		if (hp <= 0)
			return true;
		
		return false;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		
		batch.end();
		
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		 
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		Gdx.gl.glLineWidth(4);
		shapeRenderer.line(pos.x+.2f, pos.y+1.1f, pos.x+.2f+.6f*(hp/starthp), pos.y+1.1f);
		shapeRenderer.end();
		 
		batch.begin();
		
	}
	
	public void die(TDMap map) {
		if (playedDeathAnim)
			return;
		
		playedDeathAnim = true;
		
		EntityBloodyDeath b = new EntityBloodyDeath(pos);
		map.addEntity(b);
	}
	
	@Override
	public boolean update(TDMap map, float delta) {
		
		if (isDead()) {
			die(map);
			return true; // I'm dead!
		}
		
		stateTime = stateTime + delta;

		float newX = pos.x + vel.x*delta;
		float newY = pos.y + vel.y*delta;
		
		pos.x = newX;
		pos.y = newY;
		
		updateBoundingBox();
		
		return false; 
	}

	protected void updateVelFromDirection() {
		if (curDir == EntityDirectionalSprite.DIR_RIGHT)
			this.vel = new Vector2(scalarVel, 0);
		else if (curDir == EntityDirectionalSprite.DIR_LEFT)
			this.vel = new Vector2(-scalarVel, 0);
		else if (curDir == EntityDirectionalSprite.DIR_UP)
			this.vel = new Vector2(0, scalarVel);
		else 
			this.vel = new Vector2(0, -scalarVel);
	}	
	
	public boolean isDead() {
		return hp <= 0;
	}
}
