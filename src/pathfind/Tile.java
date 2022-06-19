package pathfind;

public class Tile {
	public int x, y;
	private int gCost, hCost, fCost;
	/*
	 * Gcost = distance from starting tile
	 * Hcost = distance from the end tile
	 * Fcost = gcost - hcost
	 */
	private boolean isSolid;
	private boolean isStart;
	private boolean isGoal;
	private boolean isClose;
	private Tile parentTile;
	
	
	Tile(int x, int y){
		parentTile = null;
		isClose = false;
		isStart = false;
		isGoal = false;
		isSolid = false;
		this.x = x;
		this.y = y;
	}
	
	public int getHCost() { return hCost;}
	public boolean isTileSolid() { return isSolid; }
	public void setTile(boolean state) { isSolid = state; }
	public void setStart(boolean state) { isStart = state; }
	public void setGoal(boolean state) { isGoal = state; }
	public boolean isTileStart() { return isStart; }
	public boolean isEndGoal() { return isGoal; }
	public int getFCost() { return fCost; }
	public void setParent(Tile tile) { parentTile = tile; }
	public Tile getParent() { return parentTile; }
	
	public void updateCost(Tile start, Tile end) {
		if(isSolid)return;
		
		//gCost = Math.abs(start.x - x) + Math.abs(start.y - y);
		//hCost = Math.abs(x - end.x) + Math.abs(y - end.y);
		
		int parentDistance = 0;
		if(parentTile != null) parentDistance = parentTile.gCost;
		
		gCost = (int) Math.sqrt(Math.pow(start.x - x, 2) + Math.pow(start.y - y, 2)) + parentDistance;
		hCost = (int) Math.sqrt(Math.pow(x - end.x, 2) + Math.pow(y - end.y, 2));
		fCost = gCost + hCost;
	}
	
	//Gcost = distance from starting tile
	
	
}
