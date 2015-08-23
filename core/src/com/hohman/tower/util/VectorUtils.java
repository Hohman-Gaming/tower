package com.hohman.tower.util;

import com.badlogic.gdx.math.Vector2;

public class VectorUtils {

	public static float distanceBetweenPoints(Vector2 xy1, Vector2 xy2) {
		return (float)Math.sqrt((xy1.x-xy2.x)*(xy1.x-xy2.x)+(xy1.y-xy2.y)*(xy1.y-xy2.y));
	}
	
}
