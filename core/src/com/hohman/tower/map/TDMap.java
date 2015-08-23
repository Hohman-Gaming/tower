package com.hohman.tower.map;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.hohman.tower.entity.EntityBase;
import com.hohman.tower.entity.EntityEnemy;
import com.hohman.tower.entity.EntityTower;

public abstract class TDMap  {

	public final static int CELL_DX=32;
	public final static int CELL_DY=32;
	
	public TiledMap tiledMap = null;
	protected Vector2 startingPosition = null;
	protected List<EntityBase> entities = new ArrayList<EntityBase>();
	protected float gametime = 0;
	protected CellInfo[][] cellInfoMap;

	protected boolean updating = false;
	protected List<EntityBase> entitiesAddAtEndOfUpdate = new ArrayList<EntityBase>();
	
	protected float lastSpawnTime = 0; 

	public TDMap() {
	}
	
	protected abstract boolean stillSpawning();
	protected abstract float getSpawnRate();
	protected abstract EntityEnemy spawnEntity(Vector2 pos);
	
	public void load(String mapName) {
		tiledMap = new TmxMapLoader().load(mapName);
		cellInfoMap = new CellInfo[getMapWidth()][getMapHeight()];
		
		// init cellInfo's
		for(int x=0;x<getMapWidth();x++)
			for(int y=0;y<getMapHeight();y++)
				cellInfoMap[x][y] = new CellInfo();

		int startX=0, startY=0;
		for(int x=0;x<getMapWidth();x++)
			for(int y=0;y<getMapHeight();y++) {
				Cell cell = getCellAtIndexes(x, y);
				if (cell != null) {
					String isStart = cell.getTile().getProperties().get("start", String.class);
					if (isStart != null && isStart.equals("true")) {
						startX = x;
						startY = y;
						break;
					}
				}
			}

		startingPosition = new Vector2(startX, startY);
	}
	
	public void update(float delta) {
		gametime += delta;
		updating = true;
		
		if (stillSpawning() && (gametime - lastSpawnTime) > getSpawnRate()) {
			Vector2 pos = getStartingPosition();
			addEntity(spawnEntity(pos));
			lastSpawnTime = gametime;
		}
		
		List<EntityBase> dead = new ArrayList<EntityBase>();
		
		for(EntityBase entity : entities) {
			if (entity.update(this, delta)) {
				dead.add(entity);
				if (entity instanceof EntityEnemy)
					System.out.println("Killing night");
			}
		}
		
		entities.addAll(entitiesAddAtEndOfUpdate);
		entities.removeAll(dead);
		entitiesAddAtEndOfUpdate.clear();
		updating = false;
	}
	
	public void addEntity(EntityBase entity) {
		// if in middle of update loop, don't add it directly, postpone until end of updating...
		if (updating)
			entitiesAddAtEndOfUpdate.add(entity);
		else
			entities.add(entity);
	}

	public TiledMapTileLayer getMapLayer() {
		return (TiledMapTileLayer)tiledMap.getLayers().get(0);
	}

	public void attemptPlaceTower(Vector2 mouseCoords) {
		System.out.println("mouse: " + mouseCoords + " | cell: " + TDMap.screen2CellIndexes(mouseCoords));
		if (canPutTowerHere(mouseCoords)) {
			// create a new tower here!
			Vector2 indexes = TDMap.screen2CellIndexes(mouseCoords);
			EntityTower tower = new EntityTower(indexes, .2f, 3);
			addEntity(tower);
			CellInfo cellInfo = getCellInfoAtIndexes(indexes);
			cellInfo.tower = tower;
		}

	}
	
	/* ****************************************************
	 * CELL
	   **************************************************** */
	public CellInfo getCellInfoAtIndexes(Vector2 indexes) {
		return cellInfoMap[(int)indexes.x][(int)indexes.y];
	}

	public CellInfo getCellInfoAtScreenCoordinates(Vector2 screenCoords) {
		return getCellInfoAtIndexes(screen2CellIndexes(screenCoords));
	}

	/* ****************************************************
	 * CELL
	   **************************************************** */
	public boolean isEndTile(Vector2 mapCoords) {
		Cell cell = getCellAtMapCoordinates(mapCoords);
		if (cell != null) {
			String isStart = cell.getTile().getProperties().get("end", String.class);
			if (isStart != null && isStart.equals("true"))
				return true;
		}
		
		return false;
	}

	public Cell getCellAtIndexes(int x, int y) {
		return getMapLayer().getCell(x, y);
	}
	
	public Cell getCellAtIndexes(Vector2 indexes) {
		return getMapLayer().getCell((int)indexes.x, (int)indexes.y);
	}
	
	public Cell getCellAtMapCoordinates(Vector2 mapCoords) {
		return getCellAtIndexes(TDMap.mapCoords2Indexes(mapCoords));
	}
	
	public Cell getCellAtScreenCoordinates(Vector2 screenCoords) {
		// get our cell x & y from our position
		return getCellAtIndexes(screen2CellIndexes(screenCoords));
	}

	public int getMapWidth() {
		return getMapLayer().getWidth();
	}

	public int getMapHeight() {
		return getMapLayer().getHeight();
	}
	
	public Vector2 getStartingPosition() {
		return startingPosition;
	}
	
	public boolean isCellOnPath(Vector2 mapCoords) {
		Cell cell = getCellAtMapCoordinates(mapCoords);
		if (cell == null)
			return true;
					
		String onPath = cell.getTile().getProperties().get("path", String.class);
		if (onPath == null || !onPath.equals("true"))
			return false;
		
		return true;
	}


	public float getGametime() {
		return gametime;
	}
	
	public boolean canPutTowerHere(Vector2 screenCoords) {
		// is it on the path?
		if (isCellOnPath(TDMap.screen2MapCoordinates(screenCoords)))
			return false;
		
		CellInfo cellInfo = getCellInfoAtScreenCoordinates(screenCoords);
		if (cellInfo == null)
			return false; // off screen
		
		// already a tower here?
		if (cellInfo.tower != null)
			return false;

		return true;
	}

	/* **************************************************************************
	 * UTILIY functions
	   ************************************************************************** */
	
	public static Vector2 screen2MapCoordinates(Vector2 pos) {
		return new Vector2(pos.x/CELL_DX, pos.y/CELL_DY);
	}
	
	public static Vector2 screen2CellIndexes(Vector2 pos) {
		return new Vector2(
				(float)Math.floor(pos.x / (float)CELL_DX),
				(float)Math.floor(pos.y / (float)CELL_DY)
				);
	}
	
	public static Vector2 mapCoords2Indexes(Vector2 mapCoords) {
		return new Vector2(
				(float)Math.floor(mapCoords.x),
				(float)Math.floor(mapCoords.y)
				);
	}
	
	public List<EntityBase> getEntities() {
		return entities;
	}
}
