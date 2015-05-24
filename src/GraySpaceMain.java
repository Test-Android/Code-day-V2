import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public class GraySpaceMain extends JFrame
{
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	static Player p;
	public static void bindKeys(JFrame jFrame, Player a)
	{
		p = a;
		JRootPane j = jFrame.getRootPane();
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "pressedUP");
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "releasedUP");
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "pressedLEFT");
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"), "releasedLEFT");
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "pressedRIGHT");
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"), "releasedRIGHT");
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "pressedDOWN");
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "releasedDOWN");

		j.getActionMap().put("pressedUP", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("boop");
				p.setUp(true);
			}
		});
		j.getActionMap().put("releasedUP", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("boop");

				p.setUp(false);
			}	
		});
				j.getActionMap().put("pressedLEFT", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("boop");

				p.setLeft(true);
			}
		});
		j.getActionMap().put("releasedLEFT", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("boop");

				p.setLeft(false);
			}	
		});
		
		j.getActionMap().put("pressedRIGHT", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("boop");

				p.setRight(true);
			}
		});
		j.getActionMap().put("releasedRIGHT", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("boop");

				p.setRight(false);
			}	
		});
		j.getActionMap().put("pressedDOWN", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("boop");

				p.setDown(true);
			}
		});
		j.getActionMap().put("releasedDOWN", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("boop");

				p.setDown(false);
			}	
		});
	}
	public void update()
	{
	
	}
	public static void main(String args[])
	{
		Game game = new Game();
		game.start();
	}
}
