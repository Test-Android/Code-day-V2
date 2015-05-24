import java.awt.Color;
import java.awt.Graphics2D;

//do you like the code   bc i do
public class Player
{
	private boolean up,
					left,
					right,
					down;
	private boolean alive, moving;
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
		moving = false;
		this.x = x;
		this.y = y;
		this.insetsLeft = insetsLeft;
		this.insetsTop = insetsTop;
	}

	public void update()
	{
		if(x == 38)
		{
			System.out.println("YOU WIN!!!");
			Game.win();
		}
		if(x == 0)
		{
			Game.loose();
		}
		else
		{
			if(moving)
			{
				if(right && x + 1 < 39)
				{
					if(grid.grid[x][y] == 1)
						x--;
					else
						x++;
				}
				else if(left && x - 1 >= 0)
				{
					if(grid.grid[x][y] == 1)
						x--;
					else
						x--;
				}
				else if(up && y - 1 >= 0)
				{
					if(grid.grid[x][y] == 1)
					{
						x--;
						y--;
					}
					else
						y--;
				}
				else if(down && y + 1 < 29)
				{
					if(grid.grid[x][y] == 1)
					{
						x--;
						y++;
					}	
					else
						y++;
				}
			}
			else
			{
				if(grid.grid[x][y] == 1)
				{
					x--;
				}
			}
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
		moving = b;
	}
	public void setLeft(boolean b)
	{
		left = b;
		moving = b;
	}
	public void setRight(boolean b)
	{
		right = b;
		moving = b;
	}
	public void setDown(boolean b)
	{
		down = b;
		moving = b;
	}
	public void setState(boolean b)
	{
		alive = b;
	}
}
