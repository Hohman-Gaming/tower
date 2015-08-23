package com.hohman.tower.map.levels;

import com.badlogic.gdx.math.Vector2;
import com.hohman.tower.entity.EntityEnemy;
import com.hohman.tower.entity.EntityKnight;
import com.hohman.tower.map.TDMap;

public class TDMapLevel1 extends TDMap {

	protected int numKnightsToSpawn = 20;
	
	public TDMapLevel1() {
		load("maps/map1.tmx");
	}
	
	@Override
	protected float getSpawnRate() {
		return 1f; // spawn every second
	}

	@Override
	protected EntityEnemy spawnEntity(Vector2 pos) {
		Vector2 vel = new Vector2(4,0);
		numKnightsToSpawn--;
		return new EntityKnight(pos, vel);
	}

	@Override
	protected boolean stillSpawning() {
		return numKnightsToSpawn > 0;
	}
}
