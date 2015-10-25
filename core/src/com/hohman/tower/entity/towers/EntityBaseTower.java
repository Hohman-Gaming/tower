package com.hohman.tower.entity.towers;

import com.badlogic.gdx.math.Vector2;
import com.hohman.tower.entity.EntityBase;
import com.hohman.tower.entity.EntityEnemy;
import com.hohman.tower.entity.EntitySimpleSprite;
import com.hohman.tower.entity.ammo.AmmoSausage;
import com.hohman.tower.map.TDMap;


public abstract class EntityBaseTower extends EntitySimpleSprite {

	protected EntityEnemy target = null; // our current target
	protected float lastFireTime = 0f;
	protected float secondsBetweenShots = Float.MAX_VALUE;
	protected float shotLength = .5f;
	protected float range = 1f;
	
	public EntityBaseTower(String spriteAssetName, Vector2 pos, float secondsBetweenShots, float range) {
		super(spriteAssetName, 32, 32, 0, 0, pos, new Vector2(0,0));
		this.secondsBetweenShots = secondsBetweenShots; 
		this.range = range;
	}
	
	@Override
	public boolean update(TDMap map, float delta) {
		updateTarget(map); // updates our target if necessary...
		
		if ((map.getGametime() - lastFireTime) > secondsBetweenShots && target != null) {
			// time to fire...
			lastFireTime = map.getGametime();
			AmmoSausage ammo = new AmmoSausage(new Vector2(pos.x+.5f, pos.y+.5f), target, range);
			map.addEntity(ammo);
		}
		
		return false; // haven't dealt with when I get destroyed yet
	}
	
	protected void updateTarget(TDMap map) {
		
		// never acquire a target if we already have one...
		if (target != null) {
			
			// see if target already dead
			if (target.isDead())
				target = null; // will re-acquire below
			else {
				// check to see if still a valid distance away
				float d = (float)Math.sqrt(Math.pow(pos.x - target.pos.x, 2f) + Math.pow(pos.y - target.pos.y, 2f));
				if (d > range)
					target = null;  // will re-acquire below if possible
				else
					return; // still in range!
			}
		}
		
		// don't have a target, find the closest enemy
		float currentClosest = Float.MAX_VALUE;
		for(EntityBase entity : map.getEntities()) {
			
			if (!(entity instanceof EntityEnemy))
				continue;
			
			float d = (float)Math.sqrt(Math.pow(pos.x - entity.pos.x, 2f) + Math.pow(pos.y - entity.pos.y, 2f));
			if (d < range && d < currentClosest) {
				currentClosest = d;
				target = (EntityEnemy)entity;
			}
		}
	}
}
