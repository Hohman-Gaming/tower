package com.hohman.tower.entity;

import com.badlogic.gdx.math.Vector2;

public class EntityKnight extends EntityEnemy {

	public EntityKnight(Vector2 pos, Vector2 vel) {
		super("enemy/enemy1.png", 32, 32, Math.abs(.3f/(vel.x+vel.y)), 0, 0, 3, pos, vel, 100);
	}

}
