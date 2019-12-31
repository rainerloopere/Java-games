package othello;


public class Square2 {
	
	private final int locationX;
	private final int locationY;
	private buttonValues button;
	
	enum buttonValues {
		EMPTY,
		BLACK,
		WHITE,
		ACTIVE
	}
	
	public Square2(int locationX, int locationY, buttonValues button)
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
	public boolean isActive()
	{
		return button == buttonValues.ACTIVE;
	}
	
	public String toString()
	{
		return "[" + locationX + "," + locationY + "," + button + "]";
	}
}
