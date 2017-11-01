

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Display {

	private JFrame f;
	private Canvas c;
	private String title;
	private int width, height;

	public Display(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;

		buildTheDisplay();
	}
	

	public void buildTheDisplay(){

		// To make a frame you need to:
		// Set the size, set the close operator, set the resizeable, set the location, set the visability

		// To make a canvas you need to:
		// set size, set max and min, set focasables

		// You also need need to put the canvas in the frame.
		// The frame is like the backbone and the canvas is what you draw on.


		f = new JFrame(title);
		f.setSize(width, height);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);

		c = new Canvas();
		c.setPreferredSize(new Dimension(width, height));
		c.setMaximumSize(new Dimension(width, height));
		c.setMinimumSize(new Dimension(width, height));
		c.setFocusable(false);

		f.add(c);
		f.pack();
	}


	public Canvas getCanvas(){
		return c;
	}

	public JFrame getFrame(){
		return f;
	}

}