//what do you want from me
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

//this is the main game thread class
public class Game extends JFrame implements Runnable
{
	private Thread thread;
	private boolean running = false;
	private final String NAME = "Gray Space ";
	BufferedImage backBuffer;
	Graphics g;
	Player player;
	static grid grid;
	static int level = 1;
	static boolean win, lose;
	static int speed;
	
	public Game(int speed)
	{
		this.setTitle(NAME);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setVisible(true);
		this.setResizable(false);
		
		this.setBounds(0,0,GraySpaceMain.WIDTH + getInsets().right,GraySpaceMain.HEIGHT + getInsets().bottom);
		
		backBuffer = new BufferedImage(640 + getInsets().right,480 + getInsets().bottom,BufferedImage.TYPE_INT_RGB);
		player = new Player(0,15,getInsets().left,getInsets().top);
		grid = new grid((GraySpaceMain.WIDTH / 16), (GraySpaceMain.HEIGHT / 16),player, getInsets().left, getInsets().top);
		
		this.speed = speed;
		level = this.speed/100;
		
		win = false;
		lose = false;
		GraySpaceMain.bindKeys(this,player);
	}
	
	public synchronized void start()
	{
		running = true;
		thread = new Thread(this,"Display");
		thread.start();
	}
	
	public synchronized void stop()
	{
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("It failed inside the stop method:)");
		}
	}
	
	public void run()
	{
		long lastTime = System.nanoTime();
		long now = 0;
		long nowMilis = 0;
		long lastMilis = 0;

		while(running)
		{
			now = System.nanoTime();
			nowMilis = now / 1000000;
			lastMilis = lastTime / 1000000;
			
			//the lower this is the faster the game will go...
			//250 would be 1/4 of a second... 500 would be a half... 
			if(nowMilis - lastMilis > speed)
			{
				update();
				lastTime = now;
			}
			render();
		}
	}
	
	public void update()
	{
		grid.update();
	}
	
	public void render()
	{
		Graphics2D g = (Graphics2D)getGraphics();
        Graphics2D bbg = (Graphics2D)backBuffer.getGraphics();
        bbg.clearRect(0, 0,640 + getInsets().right, getInsets().bottom + 480);
        if(win)
        {
        	lose = false;
        	bbg.setFont(new Font("Arial",Font.BOLD,40));
        	bbg.drawString("You beat level " + level + "!",175,240); 
            g.drawImage(backBuffer, 0, 0, this); 
            try
            {
            	thread.sleep(5000);
            	win = false;
            	grid.emptyGrid();
            } catch(Exception e){}
        }
        else if(lose)
        {
        	win = false;
        	bbg.setFont(new Font("Arial",Font.BOLD,40));
        	bbg.drawString("You lost :(",240,240);
        	bbg.setFont(new Font("Arial", Font.BOLD,20));
        	bbg.drawString("sorry starting over...", 240,300);
            g.drawImage(backBuffer, 0, 0, this); 
            try
            {
            	thread.sleep(5000);
            	lose = false;
            	grid.emptyGrid();
            } catch(Exception e){}
        }
        else
        {
        	grid.render(bbg);
            g.drawImage(backBuffer, 0, 0, this); 
        }
	}
	public static void win()
	{
		win = true;
		level++;
		speed-=10;
	}
	public static void lose()
	{
		lose = true;
		grid.emptyGrid();
		speed = 100;
		level = 1;
	}
	public void dispose()
	{
		this.dispose();
	}
}
