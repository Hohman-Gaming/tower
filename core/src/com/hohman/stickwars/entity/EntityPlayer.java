package com.hohman.stickwars.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class EntityPlayer extends EntityBase {

	protected static Vector2 size = new Vector2(20,20);
	protected static ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	public EntityPlayer(Vector2 pos) {
		super(pos, new Vector2(0,0), size);
	}

	@Override
	public void render(SpriteBatch batch) {
		// draw me a colored circle
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(1, 0, 0, 1);
		shapeRenderer.circle(pos.x, pos.y, size.x/2);
		shapeRenderer.end();
		
	}

}
