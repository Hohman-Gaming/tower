package com.hohman.stickwars.map.levels;

import com.badlogic.gdx.math.Vector2;
import com.hohman.stickwars.entity.EntityBumper;
import com.hohman.stickwars.map.TDMap;
import com.hohman.stickwars.util.RGB;

public class TDMapRandom extends TDMap {

	protected static RGB bgColor = new RGB(1,1,1);
	protected static int numRandomLines = 10;
	
	
	public TDMapRandom() {
		
		float dx = this.getMapWidth();
		float dy = this.getMapHeight();
		
		for(int i=0;i<numRandomLines;i++) {
			// generating a random x,y
			float x = (float)Math.random() * dx;
			float y = (float)Math.random() * dy;
			
			float x2 = x+(float)Math.random()*500;
			float y2 = y+(float)Math.random()*500;
			
			EntityBumper e = new EntityBumper(new Vector2(x,y), new Vector2(x2,y2), (float)Math.random()*10);
			this.addEntity(e);
		}
		
	}

	@Override
	protected RGB getBackgroundColor() {
		return bgColor;
	}
	
	
}
