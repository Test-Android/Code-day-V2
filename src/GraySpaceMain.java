import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
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
				p.setUp(true);
				p.setDown(false);
				p.setRight(false);
				p.setLeft(false);
			}
		});
		j.getActionMap().put("releasedUP", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				p.setUp(false);
			}	
		});
				j.getActionMap().put("pressedLEFT", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				p.setUp(false);
				p.setDown(false);
				p.setRight(false);
				p.setLeft(true);
			}
		});
		j.getActionMap().put("releasedLEFT", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				p.setLeft(false);
			}	
		});
		
		j.getActionMap().put("pressedRIGHT", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				p.setUp(false);
				p.setDown(false);
				p.setRight(true);
				p.setLeft(false);
			}
		});
		j.getActionMap().put("releasedRIGHT", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				p.setRight(false);
			}	
		});
		j.getActionMap().put("pressedDOWN", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				p.setUp(false);
				p.setDown(true);
				p.setRight(false);
				p.setLeft(false);
			}
		});
		j.getActionMap().put("releasedDOWN", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				p.setDown(false);
			}	
		});
	}
	public void update()
	{
	
	}
	public static void main(String args[])
	{
		JDialog bg = new JDialog();
		bg.setBackground(Color.BLUE);
		bg.setTitle("Settings");
		bg.setLayout(null);
		bg.setBounds(0,0,300,100);
		JLabel info = new JLabel("Enter a number from one to ten.\nTen is slow, one is fast");
		info.setBounds(0,0,300,20);
		bg.add(info);
		JTextArea speedText = new JTextArea("0");
		speedText.setBounds(0,20,300,20);
		bg.add(speedText);
		JButton okay = new JButton();
		okay.addActionListener(new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int speed;
				try
				{
					speed = Integer.parseInt(speedText.getText()) * 10;
				}
				catch(Exception e)
				{
					speed = 100;
				}
				bg.dispose();
				Game game = new Game(speed);
				game.start();
			}
		});
		okay.setBounds(75,40,100,20);
		bg.add(okay);
		bg.setVisible(true);
	}
}
