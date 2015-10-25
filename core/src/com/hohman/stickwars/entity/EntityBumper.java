package com.hohman.stickwars.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class EntityBumper extends EntityBase {

	protected static ShapeRenderer shapeRenderer = new ShapeRenderer();
	protected Vector2 posLeft, posRight;
	protected float lineWidth;
	
	
	public EntityBumper(Vector2 posLeft, Vector2 posRight, float lineWidth) {
		// OLIVER todo - figure out size
		super(posLeft, new Vector2(0,0), new Vector2(Math.abs(posRight.x-posLeft.x), Math.abs(posRight.y-posRight.y)));
		this.posLeft = posLeft;
		this.posRight = posRight;
		this.lineWidth = lineWidth;
	}

	@Override
	public void render(SpriteBatch batch) {
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		Gdx.gl20.glLineWidth(lineWidth);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0, 0, 0, 1);
		shapeRenderer.rectLine(posLeft.x, posLeft.y, posRight.x, posRight.y, lineWidth);
		shapeRenderer.end();
	}

}
