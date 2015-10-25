package com.hohman.stickwars.entity.ammo;

import com.badlogic.gdx.math.Vector2;
import com.hohman.stickwars.entity.EntityBase;
import com.hohman.stickwars.entity.EntityEnemy;
import com.hohman.stickwars.map.TDMap;
import com.hohman.stickwars.util.VectorUtils;

public abstract class AmmoBase extends EntityBase {

	protected float damagePerHit;
	protected EntityEnemy target;
	protected Vector2 startingPos = null; 
	protected float maxDistance = 0;
	
	public AmmoBase(Vector2 pos, Vector2 vel, Vector2 size, float damagePerHit, EntityEnemy target, float maxDistance) {
		super(pos, vel, size);
		startingPos = pos;
		this.damagePerHit = damagePerHit;
		this.target = target;
		this.maxDistance = maxDistance;
	}

	@Override
	public boolean update(TDMap map, float delta) {
		if (super.update(map, delta))
			return true;

		if (VectorUtils.distanceBetweenPoints(startingPos, pos) > maxDistance)
			return true;
		
		if (pos.x < 0 || pos.y < 0 || pos.x > map.getMapWidth() || pos.y > map.getMapHeight())
			return true; // off screen in x/y

		// see if we hit something...
		for(EntityBase entity : map.getEntities())
			if (entity instanceof EntityEnemy)
				if (entity.getBoundingBox().intersects(this.boundingBox)) { // collision!
					((EntityEnemy) entity).processDamage(damagePerHit);
					System.out.println("Hit!");
					return true; // we should go away, we hit something
				}
		
		return false;
	}
}
