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

//this is the main game thread class
public class Game extends JFrame implements Runnable
{
	private Thread thread;
	private boolean running = false;
	private final String NAME = "Gray Space ";
	BufferedImage backBuffer;
	Graphics g;
	Player player;
	grid grid;
	int level = 1;
	static boolean win;
	int speed;
	
	public Game()
	{
		this.setTitle(NAME);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setVisible(true);
		this.setResizable(false);
		
		this.setBounds(0,0,GraySpaceMain.WIDTH + getInsets().right,GraySpaceMain.HEIGHT + getInsets().bottom);
		
		backBuffer = new BufferedImage(640 + getInsets().right,480 + getInsets().bottom,BufferedImage.TYPE_INT_RGB);
		player = new Player(0,15,getInsets().left,getInsets().top);
		grid = new grid((GraySpaceMain.WIDTH / 16), (GraySpaceMain.HEIGHT / 16),player, getInsets().left, getInsets().top);
		
		speed = 100;
		win = false;
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
<<<<<<< HEAD
			if(nowMilis - lastMilis > 100)
=======
			if(nowMilis - lastMilis > speed)
>>>>>>> origin/master
			{
				update();
				lastTime = now;
			}
			render();
		}
	}
	
	public void update()
	{
/*		if(playerX + 1 < 640/16)
			playerX++;
		else
			playerX = 1; */
		grid.update();
	}
	
	public void render()
	{
		Graphics2D g = (Graphics2D)getGraphics();
        Graphics2D bbg = (Graphics2D)backBuffer.getGraphics();
        
//        bbg.setColor(Color.WHITE);
        /*bbg.fillRect(0, 0, GraySpaceMain.WIDTH, GraySpaceMain.HEIGHT);  
        bbg.setColor(Color.black);
        bbg.fillRect(x, y, 32, 32);*/
/*        BufferedImage hero = null;
        try
        {
        	hero = ImageIO.read(new File("Code-Day/GraySpace/res/randome3.png"));
        } catch(Exception e){} */
        bbg.clearRect(0, 0,640 + getInsets().right, getInsets().bottom + 480);
//      bbg.drawImage(hero,getInsets().top,getInsets().left,hero.getWidth(),hero.getHeight(),null);
        if(!win)
        {
        	grid.render(bbg);
            g.drawImage(backBuffer, 0, 0, this); 
        }
        else
        {
        	bbg.setFont(new Font("Arial",Font.BOLD,40));
        	bbg.drawString("You beat level " + level + "!",240,240);
            g.drawImage(backBuffer, 0, 0, this); 
            try
            {
            	thread.sleep(5000);
            	win = false;
            	speedUp();
            	grid.emptyGrid();
            } catch(Exception e){}

        }
	}
	public static void win()
	{
		win = true;
	}
	public void speedUp()
	{
		level++;
		speed-=10;
	}
	public void loose()
	{
		thread.stop();
		Graphics2D g = (Graphics2D)getGraphics();
		Graphics2D bbg = (Graphics2D)backBuffer.createGraphics();
		bbg.clearRect(0, 0, 640, 480);
		bbg.setFont(new Font("Arial",Font.BOLD,40));
		bbg.drawString("You Lose!", 160, 320);
		JDialog bg = new JDialog();
		bg.setTitle("Want to continue?");
		bg.setLayout(null);
		JLabel info = new JLabel("Click okay to continue and start at level 1. \nHit stop to close out of the game.");
		info.setBounds(0,100,400,200);
		bg.add(info);
		bg.setBounds(0, 0, 400, 400);
		JButton okay = new JButton("Okay");
		okay.addActionListener(new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Game g = new Game();
				g.start();
			}
		});
		okay.setBounds(0,380,100,20);
		bg.add(okay);
		JButton stop = new JButton("Stop");
		stop.addActionListener(new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		stop.setBounds(300,380,100,20);
		bg.add(stop);
		bg.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		bg.setVisible(true);
		
	}
	public void dispose()
	{
		this.dispose();
	}
}
