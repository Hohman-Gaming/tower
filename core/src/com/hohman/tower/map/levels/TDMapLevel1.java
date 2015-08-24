package com.hohman.tower.map.levels;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.hohman.tower.entity.EntityEnemy;
import com.hohman.tower.entity.EntityKnight;
import com.hohman.tower.entity.towers.EntityBaseTower;
import com.hohman.tower.entity.towers.SimpleTower;
import com.hohman.tower.entity.towers.SimpleTower2;
import com.hohman.tower.entity.towers.SimpleTower3;
import com.hohman.tower.map.TDMap;

@SuppressWarnings("serial")
public class TDMapLevel1 extends TDMap {

	protected int numKnightsToSpawn = 20;
	protected List<EntityBaseTower> towersAvailable = new ArrayList<EntityBaseTower>() {{
		add(new SimpleTower(new Vector2(0,0), 0, 0));
		add(new SimpleTower2(new Vector2(0,0), 0, 0));
		add(new SimpleTower3(new Vector2(0,0), 0, 0));
	}};
	
	
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
	
	@Override
	protected List<EntityBaseTower> getTowersAvailable() {
		return towersAvailable;
	}
}
