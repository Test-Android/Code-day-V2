import java.awt.Color;
import java.awt.Graphics2D;

//do you like the code
public class Player
{
	private boolean up,
					left,
					right,
					down;
	private boolean alive;
	private int x,
				y;
	private int gravity;
	private int insetsLeft,
				insetsTop;
	
	public Player(int x,int y, int insetsLeft, int insetsTop)
	{
		up = false;
		left = false;
		right = false;
		down = false;
		alive = true;
		this.x = x;
		this.y = y;
		this.insetsLeft = insetsLeft;
		this.insetsTop = insetsTop;
	}

	public void update()
	{
		if (left && x - 1 > -1)
		{
			x-=1;
		}
		else if (right && x + 1 < 30)
		{
			x+=1;
		}
		if(up && y - 1 > -1 )
		{
			y-=1;
		}
		else if(down && y + 1 < 30)
		{
			y+=1;
		}
	}
	public void render(Graphics2D bbg)
	{
		if(alive)
		{
			bbg.setColor(Color.WHITE);
			bbg.fillRect(x*16 + insetsLeft, y*16  + insetsTop, 16, 16);
		}

	}
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public int getY()
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void setUp(boolean b)
	{
		up = b;
	}
	public void setLeft(boolean b)
	{
		left = b;
	}
	public void setRight(boolean b)
	{
		right = b;
	}
	public void setDown(boolean b)
	{
		down = b;
	}
	public void setState(boolean b)
	{
		alive = b;
	}
}
