package pathfind;

import java.util.ArrayList;
/*
 * Gcost = distance from starting tile
 * Hcost = distance from the end tile
 * Fcost = gcost - hcost
 */
public class PathFindingAlgo {
	private static ArrayList<Tile> openTile;
	private static ArrayList<Tile> closeTile;
	private static ArrayList<Tile> path;
	private static Tile[] tilemap;
	
	private static Tile start, end;
	private static boolean isPathFound = false;
	
	// debug__
	
	private static Tile debug_currentTile;
	
	PathFindingAlgo(Tile[] map){
		tilemap = map;
		
		
		
		path = new ArrayList<Tile>();
		openTile = new ArrayList<Tile>();
		closeTile = new ArrayList<Tile>();
	}
	
	public static void reset() {
		if(path != null)
			path.clear();
		
		openTile.clear();
		closeTile.clear();
	}
	
	public static  ArrayList<Tile> _debug_getPath() {return path;}
	public static ArrayList<Tile> getPath() {
		
		if(!isPathFound) return null;
		
		
		
		Tile current = end.getParent();
		
		while(current != start) {
			path.add(current);
			current = current.getParent();
		}
		
		return path;
	}
	
	
	
	public static void initialize(Tile st, Tile en) {
		start = st;
		end = en;
		
		
		debug_currentTile = start;
		//debug
		openTile.add(debug_currentTile);
		calculateNeighbor(debug_currentTile);
	}
	
	private static boolean alreadyInCloseList(Tile tile) {
		
		for(int i = 0; i < closeTile.size(); i++) {
			if(tile == closeTile.get(i))return true;
		}
		return false;
	}
	
	
	private static boolean alreadyInOpenList(Tile tile) {
		for(int i = 0; i < openTile.size(); i++) {
			if(tile == openTile.get(i))return true;
		}
		return false;
	}
	
	private static void calculateNeighbor(Tile tile) {
		int x = tile.x, y = tile.y;
		
		ArrayList<Tile> neighbor = new ArrayList<Tile>();
		
		if(y < 19) {
			neighbor.add(tilemap[x + (y + 1) * TileMap.width]);
		}
		
		if(y > 0) {
			neighbor.add(tilemap[x + (y - 1) * TileMap.width]);
		}
		if(x < 19) {
			neighbor.add(tilemap[(x + 1) + y * TileMap.width]);
		}
		if(x > 0) {
			neighbor.add(tilemap[(x - 1) + y * TileMap.width]);
		}
		
		
		if(y < 19 && x < 19) {
			neighbor.add(tilemap[(x + 1) + (y + 1) * TileMap.width]);
		}

		if(y < 19 && x > 0) {
			neighbor.add(tilemap[(x - 1) + (y + 1) * TileMap.width]);
		}
		
		if(y > 0 && x > 0) {
			neighbor.add(tilemap[(x - 1) + (y - 1) * TileMap.width]);
		}
		
		if(y > 0 && x < 19) {
			neighbor.add(tilemap[(x + 1) + (y - 1) * TileMap.width]);
		}
		
		
		for(int i = 0; i < neighbor.size(); i++) {
			if(neighbor.get(i).isTileSolid())continue;
			if(alreadyInCloseList(neighbor.get(i))) { continue;}
			
			neighbor.get(i).setParent(tile);
			neighbor.get(i).updateCost(tile, end);
			if(!alreadyInOpenList(neighbor.get(i))) {
				openTile.add(neighbor.get(i));
			}
		}
		
		
		
		
		
		
	}
	
	private static Tile findLowestFCost() {
		if(openTile.size() == 0)return null;
		
		
		int lowest = Integer.MAX_VALUE;
		int index = 0;
		for(int i = 0; i < openTile.size(); i++) {
			if(openTile.get(i).getFCost() < lowest) {
				lowest = openTile.get(i).getFCost();
				index = i;
			}
		}
		
		return openTile.get(index);
	}
	
	public static void _debug_path_find() {
		
		isPathFound = true;
		
		debug_currentTile = findLowestFCost();
		path.add(debug_currentTile);
		openTile.remove(debug_currentTile);
		if(!alreadyInCloseList(debug_currentTile))
		closeTile.add(debug_currentTile);
		
		//if(debug_currentTile == end)isPathFound = true;
		
		calculateNeighbor(debug_currentTile);
		System.out.println("Current X: " + debug_currentTile.x + " Y: " + debug_currentTile.y);
		
	}
	
	public static void findPath() {
		Tile currentTile = start;
		openTile.add(currentTile);
		calculateNeighbor(currentTile);
		
		while(currentTile != end && !openTile.isEmpty()) {
			currentTile = findLowestFCost();
			openTile.remove(currentTile);
			if(!alreadyInCloseList(currentTile))
			closeTile.add(currentTile);
			
			if(currentTile == end) { 
				isPathFound = true;
				return;
			}
			
			calculateNeighbor(currentTile);
			
		}
		
		
	}
	
	
}
