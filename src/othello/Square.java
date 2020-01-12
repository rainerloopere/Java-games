package othello;

//Simple class to group X and Y locations on the board and the colour of the square.
public class Square {
	
	private final int locationX;
	private final int locationY;
	private buttonValues button;
	
	enum buttonValues {
		EMPTY,
		BLACK,
		WHITE
	}
	
	public Square(int locationX, int locationY, buttonValues button)
	{
		this.locationX = locationX;
		this.locationY = locationY;
		this.button = button;
	}
//	Using second constructor to create deep copies of Squares without dependencies.
	public Square(Square that)
	{
		this.locationX = that.locationX;
		this.locationY = that.locationY;
		this.button = that.button;
	}
	
	public int getLocationX() 
	{
		return locationX;
	}

	public int getLocationY() 
	{
		return locationY;
	}

	public buttonValues getButton() 
	{
		return button;
	}
	public void setButton(buttonValues button)
	{
		this.button = button;
	}
	public boolean isEmpty()
	{
		return button == buttonValues.EMPTY;
	}
	public boolean isWhite()
	{
		return button == buttonValues.WHITE;
	}
	public boolean isBlack()
	{
		return button == buttonValues.BLACK;
	}
	
	public String toString()
	{
		return "[" + locationX + "," + locationY + "," + button + "]";
	}

}
