package othello;


public class Square {
	
	private final int locationX;
	private final int locationY;
	private String button;
	
	public Square(int locationX, int locationY, String button)
	{
		this.locationX = locationX;
		this.locationY = locationY;
		this.button = button;
	}
	
	public int getLocationX() 
	{
		return locationX;
	}

	public int getLocationY() 
	{
		return locationY;
	}

	public String getButton() 
	{
		return button;
	}
	public void setButton(String button)
	{
		this.button = button;
	}
	public String toString()
	{
		return "[" + locationX + "," + locationY + "," + button + "]";
	}
}
