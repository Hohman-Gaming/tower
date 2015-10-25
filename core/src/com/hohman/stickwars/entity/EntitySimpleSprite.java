package com.hohman.stickwars.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class EntitySimpleSprite extends EntityBase {

	protected TextureRegion textureRegion = null;
	
	public EntitySimpleSprite(String textureFileName, int texturePixelWidth, int texturePixelHeight, int startX, int startY, Vector2 pos, Vector2 vel) {
		
		super(pos, vel, new Vector2(texturePixelWidth, texturePixelHeight));
		
		textureRegion = new TextureRegion(
				new Texture(Gdx.files.internal(textureFileName)), 
				startX, 
				startY, 
				texturePixelWidth, 
				texturePixelHeight);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(textureRegion, pos.x, pos.y, 1, 1);
	}
	
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}
	
}
