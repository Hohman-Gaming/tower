package com.hohman.tower.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.hohman.tower.map.TDMap;

public class EntityDirectionalSprite extends EntityBase {

	private Animation animations[] = null; // array of 4, up, right, down, left
	public static final int DIR_DOWN=0;
	public static final int DIR_LEFT=1;
	public static final int DIR_RIGHT=2;
	public static final int DIR_UP=3;
	public static final int[] DIRS = new int[] {DIR_DOWN, DIR_LEFT, DIR_RIGHT, DIR_UP};

	protected int curDir = DIR_RIGHT;
	
	public EntityDirectionalSprite(String textureFileName, int texturePixelWidth, int texturePixelHeight, float keyFrameTiming, int startX, int startY, int numFrames,
			Vector2 pos, Vector2 vel) {
		
		super(pos, vel, new Vector2(texturePixelWidth/TDMap.CELL_DX, texturePixelHeight/TDMap.CELL_DY));
		
		TextureRegion textureRegion = new TextureRegion(
				new Texture(Gdx.files.internal(textureFileName)), 
				startX*texturePixelWidth, 
				startY*texturePixelHeight, 
				texturePixelWidth*numFrames, 
				texturePixelHeight*4);
		animations = new Animation[4];
		
		TextureRegion[][] splits = textureRegion.split(texturePixelWidth, texturePixelHeight);
		for(int dir : DIRS)
			animations[dir]= new Animation(keyFrameTiming, splits[dir]);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(animations[curDir].getKeyFrame(stateTime, true), pos.x, pos.y, 1, 1);
	}
	
}
