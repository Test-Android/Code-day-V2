import java.awt.Color;
import java.awt.Graphics2D;

//do you like the code   bc i do
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
		if(up && right)
 		{
			if((grid.grid[x + 1][y] == 1) || (grid.grid[x][y -= 1] == 1))
			{
				x = x;
				y = y;
			}
			else if((grid.grid[x - 1][y] == 1) || (grid.grid[x][y += 1] == 1))
			{
				x = x;
				y = y;
			}
			else if(x + 1 < 40 && y - 1 >=0)
			{
				x++;
				y--;
			}
 		}
		
		else if(down && right)
 		{
			if((grid.grid[x + 1][y] == 1) || (grid.grid[x][y += 1] == 1))
			{
				x = x;
				y = y;
			}
			else if((grid.grid[x + 1][y] == 1) || (grid.grid[x][y -= 1] == 1))
			{
				x = x;
				y = y;
			}
			else if(x + 1 < 40 && y + 1 < 30)
			{
				x+=1;
				y+=1;
			}
 		}
		
		
		else if(right)
			if(x + 1 < 30)
			{
				if(grid.grid[x + 1][y] == 1)
				{
					x -= 1;
					System.out.println("cant move right");
				}
				else
				{
					x += 1;
				}
			}
			else if(grid.grid[x + 1][y] == 1)
			{
				x -= x;
			}
			else
			{
				x = x;
			}
		
			else if(left)
			if(x - 1 > 0)
			{
				if(grid.grid[x - 1][y] == 1)
				{
					x += 1;
					System.out.println("cant move left");
				}
				else
				{
					x -= 1;
				}
			}
			else
			{
				x = x;
			}
			else if(up)
			if(y - 1 > 0)
			{
				if(grid.grid[x][y - 1] == 1)
				{
					y = y;
				}
				else if(grid.grid[x + 1][y] == 1)
				{
					x -= 1;
					y -= 1;
				}
				else
				{
					y -= 1;
				}
			}
			else
			{
				y = y;
			}
	
		else if(down)
		{
			if(y + 1 < 29)
			{
				if(grid.grid[x][y + 1] == 1)
				{
					y = y;
				}
				else if(grid.grid[x + 1][y] == 1)
				{
					x -= 1;
					y += 1;

				} 
				else
				{
					y += 1;
				}
			}
			else
			{
				y = y;
			}
		}
		
		
		
		if(!right && !down && !up && !left)
		{
			if(grid.grid[x + 1][y] == 1)
			{
				x -= 1;
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
