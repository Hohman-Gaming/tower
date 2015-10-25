package com.hohman.tower.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TitleScreen extends BaseScreen {

	TextureRegion title;
	SpriteBatch batch;
	float time = 0;

	public TitleScreen (Game game) {
		super(game);
	}

	@Override
	public void show () {
		title = new TextureRegion(new Texture("Logo.png"), 0, 0, SCREEN_PIXELS_DX, SCREEN_PIXELS_DY);
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, SCREEN_PIXELS_DX, SCREEN_PIXELS_DY);
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(title, 0, 0);
		batch.end();

		time += delta;
		if (time > 1) {
			if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
				game.setScreen(new GameScreen(game));
			}
		}
	}

	@Override
	public void hide () {
		batch.dispose();
		title.getTexture().dispose();
	}

}
