package com.hohman.tower.entity.ammo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.hohman.tower.entity.EntityEnemy;

public class AmmoStraightLine extends AmmoBase {

	protected Vector2 targetXY = null;
	protected float shotLength = 1f;
	protected float scalarVelocity = 0f;
	protected static ShapeRenderer shapeRenderer = new ShapeRenderer();
	protected Vector2 shotLengthScaledVelocity;
	
	
	public AmmoStraightLine(Vector2 pos, float shotLength, float scalarVelocity, float damagePerHit, EntityEnemy target, float maxDistance) {
		super(pos, new Vector2(0,0), new Vector2(shotLength/2, shotLength/2), damagePerHit, target, maxDistance);  // we will dynamically calculate velocity
		targetXY = new Vector2(target.pos); // the straight-line bullet targets the original x/y of the target, regardless of what happens as target moves
		this.shotLength = shotLength;
		this.scalarVelocity = scalarVelocity;
		updateVelocity();
	}


	@Override
	public void render(SpriteBatch batch) {
		
		batch.end();
		// paint our sausage...
		float x1 = pos.x;
		float y1 = pos.y;
		
		float x2 = pos.x + shotLengthScaledVelocity.x;
		float y2 = pos.y + shotLengthScaledVelocity.y;
		
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		 
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.OLIVE);
		Gdx.gl.glLineWidth(4);
		shapeRenderer.line(x1, y1, x2, y2);
		shapeRenderer.end();
		
		batch.begin();
	}
	
	protected void updateVelocity() {
		// calculate 2 vectors U and V

		// v is a vector heading from our position to our target
		float v1 = targetXY.x - pos.x;
		float v2 = targetXY.y - pos.y;

		// arbitrarily, U is a vector towards our target
		float u1 = (v1 < 0 ? -pos.x : pos.x);
		float u2 = 0;

		// theta is the inner angle between the 2 vectors
		float theta = (float) Math.acos((((u1*v1)+(u2*v2)) / 
				(Math.sqrt(Math.pow(u1, 2f) + Math.pow(u2, 2f)) * Math.sqrt(Math.pow(v1, 2f)+Math.pow(v2, 2f)))));
		
		float opposite = (float)(scalarVelocity * Math.sin(theta));
		float adjacent = (float)(scalarVelocity * Math.cos(theta));

		// finally update our velocity vector
		this.vel = new Vector2(
				v1 < 0 ? -adjacent : adjacent, 
				v2 < 0 ? -opposite : opposite); // take the signs from the v vector

		// now get the vector for painting the shot, which is typically smaller than the velocity
		opposite = (float)(shotLength * Math.sin(theta));
		adjacent = (float)(shotLength * Math.cos(theta));

		// finally update our velocity vector
		this.shotLengthScaledVelocity = new Vector2(
				v1 < 0 ? -adjacent : adjacent, 
				v2 < 0 ? -opposite : opposite); // take the signs from the v vector
	}
	
	
}
