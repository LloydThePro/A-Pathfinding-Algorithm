package pathfind;

import javax.swing.JFrame;


import pathfind.Renderer;

public class Main {

	public static void main(String[] args) {
		JFrame myFrame = new JFrame("PathFind");
		Renderer render = new Renderer(800, 680);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLocation(0, 0);
		myFrame.setResizable(false);
		myFrame.add(render);
		myFrame.addKeyListener(render.keyinput);
		myFrame.addMouseListener(render.mouseInput);
		myFrame.pack();
		
		myFrame.setVisible(true);
		
		
		

	}

}
