package com.hohman.stickwars.entity.ammo;

import com.badlogic.gdx.math.Vector2;
import com.hohman.stickwars.entity.EntityEnemy;

public class AmmoSausage extends AmmoStraightLine {

	public AmmoSausage(Vector2 pos, EntityEnemy target, float maxDistance) {
		super(pos, .3f, 20f, 10f, target, maxDistance);  // sausages do 10 damage...
	}
}
