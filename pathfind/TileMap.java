package pathfind;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

import pathfind.Tile;


public class TileMap<AttributeCharacterIterator> {
	public static int width, height;
	private Tile tilemap[];
	private Tile endTile;
	private Tile startTile;
	private ArrayList<Tile> path;
	private static PathFindingAlgo algo;
	
	TileMap(int width, int height){
		this.width = width;
		this.height = height;
		
		
		tilemap = new Tile[width * height];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tilemap[x + y * width] = new Tile(x,y);
				tilemap[x + y * width].setTile(false);
			}
		}
		algo = new PathFindingAlgo(tilemap);
	}
	
	public void reset() {
		for(int i = 0; i < tilemap.length; i++) {
			tilemap[i].resetAttrib();
		}
		
		PathFindingAlgo.reset();
	}
	
	public void findPath() {
		//PathFindingAlgo._debug_path_find();
		PathFindingAlgo.findPath();
		path = PathFindingAlgo.getPath();
		//path = PathFindingAlgo._debug_getPath();
	}
	public Tile getTile(int x, int y) {
		return tilemap[x + y * width];
	}
	
	
	public void setStartAndEnd(int x, int y, int x2, int y2) {
		if(startTile != null) {
			startTile.setStart(false);
			startTile = null;
		}
		if(endTile != null) {
			endTile.setGoal(false);
			endTile = null;
		}
		startTile = tilemap[x + y * width];
		startTile.setStart(true);
		
		endTile = tilemap[x2 + y2 * width];
		endTile.setGoal(true);
		PathFindingAlgo.initialize(startTile, endTile);
	}
	
	public void drawTiles(int panelWidth, int panelHeight, Graphics2D g) {
		
		
		if(Renderer.mouseInput.isClick) {
			
			
			float offsetX = panelWidth / width;
			float offsetY = panelHeight / height;
			Renderer.mouseInput.isClick = false;
			float x = Renderer.mouseInput.x / offsetX - 0.2f, y = Renderer.mouseInput.y / offsetY - 0.85f;
			
			
			Tile t = tilemap[(int)x + (int)y * width];
			
			switch(Renderer.mouseInput.mouse) {
			case MouseEvent.BUTTON1:
				
				t.setTile(!t.isTileSolid());
				break;
			case MouseEvent.BUTTON2:
				endTile.setGoal(false);
				endTile = t;
				t.setGoal(true);
				PathFindingAlgo.reset();
				PathFindingAlgo.initialize(startTile, endTile);
				break;
			case MouseEvent.BUTTON3:
				
				startTile.setStart(false);
				startTile = t;
				t.setStart(true);
				PathFindingAlgo.reset();
				PathFindingAlgo.initialize(startTile, endTile);
				break;
			
			}
			
			
			
			
		}
		
		
		
		int offsetX = panelWidth / width;
		int offsetY = panelHeight / height;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				
				Tile tile = tilemap[x + y * width];
				
				if(tile.isTileSolid()) {
					g.setColor(Color.BLACK);
				}
				else if(tile.isTileStart()) {
					g.setColor(Color.blue);
				}
				else if(tile.isEndGoal()) {
					g.setColor(Color.red);
				}
				else {
					g.setColor(Color.GRAY);
				}
				
				
				if(path != null) {
					
					for(int i = 0; i < path.size(); i++) {
						Tile t = path.get(i);
						if(t.x == x && t.y == y) {
							g.setColor(Color.LIGHT_GRAY);
							g.fillRect(x * offsetX, y * offsetY, offsetX, offsetY);
						}
						
					}
				}
	
				g.setFont( new Font(g.getFont().getFontName(), Font.PLAIN, 20));
				g.fillRect(x * offsetX, y * offsetY, offsetX, offsetY);
				
				g.setColor(Color.white);
				g.drawRect(x * offsetX, (y * offsetY), offsetX, offsetY);
				
				if(tile.getFCost() > 0) {
					g.setColor(Color.CYAN);
					g.drawString(String.valueOf(tile.getFCost()),x * offsetX, y * offsetY + 25);
				}
				
				if(tile.getGCost() > 0) {
					g.setColor(Color.MAGENTA);
					g.drawString(String.valueOf((int)tile.getGCost()),x * offsetX + 23, y * offsetY + 25);
				}
				
				
			}
		}
		
		
		
	}
	
	
}
