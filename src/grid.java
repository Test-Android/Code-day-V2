import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;



public class grid 
{
	static int[][] grid ;
	static Color[][] colours1;
	static Color[][] colours2;
	int x ,  y ;
	final int mutiplyer16bit = 16 ;
	int insetLeft;
	int insetTop;
	Player p;
	boolean createColumn = false;
	int columnX = 0;
	int columnY = 0;
	int colPhase = 0;
	
	int columnCount = 0;
	public grid(int xvalue , int yvalue, Player p, int insetLeft, int insetTop)
	{
		this.insetLeft = insetLeft;
		this.insetTop = insetTop;
		this.x = xvalue ; 
		this.y = yvalue ;
		this.p = p;
		grid = new int[this.x][this.y];
		colours1 = new Color[40][30];
		colours2 = new Color[40][30];
		makeColors(colours1, colours2);
	}
	
	public void setPositionNumAndDraw(Graphics g,int x , int y, int num )
	{
		grid[x][y] = num ;
	}
	public int getPositionNum(int x , int y ){return grid[x][y] ;}
	
	public void drawplayer(Graphics g , Image i )
	{
//		g.drawImage(i, playery, playerx, mutiplyer16bit*2, mutiplyer16bit*2, null);
	}
	public void updatePlayer()
	{
		p.update();
	}
	public void makenewcolumn()
	{
		if(createColumn)
		{
			if(colPhase == 3)
			{
				createColumn = false;
			}
			else
				colPhase ++;
		}
		else
		{
			createColumn = true;
			columnX = 640/16 - 1;
			columnY = (int)(Math.random() * 480/16);
//			System.out.println("ColY: " + columnY);
			int length = (int)(Math.random() * 3) + 3;
//			System.out.println("Length: " + length);
			colPhase = 0;
			int count = 0;
			boolean cont = true;
			while(count <= length && cont)
			{
				if(columnY + count < this.y)
				{
					grid[columnX][columnY + count] = 1;
					
					count++;
				}
				else
				{
					cont = false;
				}
			}
		}
	}
	 public void update()
	 {
		for(int x = 0; x < this.x - 1; x++)
		{
			for(int y =0; y < this.y; y++)
			{
				grid[x][y] = grid[x + 1][y];
				grid[x+1][y] = 0;
			}
		}
		updatePlayer();
		makenewcolumn();
	 }
	 public void render(Graphics2D bbg)
	 {
		boolean colorChange = true;
		for(int x = 0; x < this.x; x++)
		{
			for(int y = 0; y < this.y; y++)
			{
				if(grid[x][y] == 0)
	       		{
					if(colorChange)
					{
						bbg.setColor(colours1[x][y]);
						colorChange = false;
					}
					else
					{
						bbg.setColor(colours2[x][y]);
						colorChange = true;
					}
	       			bbg.fillRect(x *16 + insetLeft, y*16 + insetTop,16,16);
	       		}
	       		else if(grid[x][y] == 1)
	       		{
	       			bbg.setColor(Color.BLACK);
	       			bbg.fillRect(x *16 + insetLeft, y*16 + insetTop,16,16);
	       		}
			}
	    }
		p.render(bbg);
	 }
	 
	 private void makeColors(Color[][] colours1, Color[][] colours2)
	 {
		 for(int x = 0; x < 40; x++)
		 {
			 for(int y = 0; y < 30; y++)
			 {
				 Color colorcolor = new Color ((int)(Math.random()*20+75), (int)(Math.random()*20+75), (int)(Math.random()*20+150));
				 colours1[x][y] = colorcolor;
			 }
		 }
		 for(int x = 0; x < 40; x++)
		 {
			 for(int y = 0; y < 30; y++)
			 {
				 Color colorcolor = new Color ((int)(Math.random()*20+75), (int)(Math.random()*20+75), (int)(Math.random()*20+150));
				 colours2[x][y] = colorcolor;
			 }
		 }
	 }

	public void emptyGrid() 
	{
		grid = new int[40][30];
		p.setX(0);
		p.setY(15);
		p.setState(true);
		
	}
}
