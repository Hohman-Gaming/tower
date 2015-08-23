package com.hohman.tower.entity.effects;

import com.badlogic.gdx.math.Vector2;
import com.hohman.tower.entity.EntitySimpleAnimation;

public class EntityBloodyDeath extends EntitySimpleAnimation {

	public EntityBloodyDeath(Vector2 pos) {
		super("enemy/blood.png", 32, 32, 0.15f, 0, 0, 6, pos, new Vector2(0,0));
	}
}
