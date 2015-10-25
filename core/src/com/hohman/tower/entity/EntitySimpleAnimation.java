package com.hohman.tower.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.hohman.tower.map.TDMap;

public class EntitySimpleAnimation extends EntityBase {

	protected Animation animation;
	
	public EntitySimpleAnimation(String textureFileName, int texturePixelWidth, int texturePixelHeight, float keyFrameTiming, int startX, int startY, int numFrames,
			Vector2 pos, Vector2 vel) {
		super(pos, vel, new Vector2(texturePixelWidth/TDMap.CELL_DX, texturePixelHeight/TDMap.CELL_DY));

		TextureRegion tr = new TextureRegion(
				new Texture(Gdx.files.internal(textureFileName)), 
				startX*texturePixelWidth, 
				startY*texturePixelHeight, 
				texturePixelWidth*numFrames, 
				texturePixelHeight);
		
		TextureRegion[][] splits = tr.split(texturePixelWidth, texturePixelHeight);
		
		animation = new Animation(keyFrameTiming, splits[0]);
		
	}

	@Override
	public boolean update(TDMap map, float delta) {
		super.update(map, delta);
		if (animation.isAnimationFinished(delta))
			return true; // I'm done...
		
		return false;
	}	
	
	@Override
	public void render(SpriteBatch batch) {
		if (animation.isAnimationFinished(stateTime))
			return;
		batch.draw(animation.getKeyFrame(stateTime, true), pos.x, pos.y, 1, 1);
	}
}
