package pathfind;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


class MouseInput implements MouseListener{

	boolean isClick = false;
	int mouse = 1;
	int x, y;
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		isClick = true;
		Point loc = e.getPoint();
		
		x = loc.x;
		y = loc.y;
		
		mouse = e.getButton();
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

class KeyBoard implements KeyListener{
	
	public boolean isKeyHit = false;
	int keyCode;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		isKeyHit = true;
		keyCode = e.getExtendedKeyCode();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

public class Renderer extends JPanel implements Runnable{
	private static int width, height;
	private boolean isRunning = true;
	private Thread renThread;
	TileMap tileMap;
	public static KeyBoard keyinput = new KeyBoard();
	public static MouseInput mouseInput = new MouseInput();
	
	Renderer(int width, int height){
		tileMap = new TileMap(20,20);
		
		
		
		tileMap.setStartAndEnd(0, 0, 12, 16);
		this.width = width;
		this.height = height;
		setVisible(true);
		setPreferredSize(new Dimension(width, height));
		setDoubleBuffered(true);
		setOpaque(true);
		
		
		renThread = new Thread(this);
		renThread.start();
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileMap.drawTiles(width, height, g2);
	
		
		g2.dispose();
	}
	
	

	@Override
	public void run() {
		
		while(isRunning) {
			
			if(keyinput.isKeyHit) {
				switch(keyinput.keyCode) {
				
				case KeyEvent.VK_SPACE:
					tileMap.reset();
					tileMap.findPath();
					break;
					
				case KeyEvent.VK_ESCAPE:
					System.out.println("fuck");
					tileMap.reset();
					break;
					
			
				}
				
				
				
				keyinput.isKeyHit = false;
				
				
			}
			
			repaint();
			
		}
		
	}
	
	
	
	
	
}
