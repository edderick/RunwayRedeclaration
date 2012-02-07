public class SquareDrawableObject extends DrawableObject{
	
	int colour;
	int xPosition;
	int yPosition;
	private int width;
	private int height;
	
	SquareDrawableObject(int x, int y, int c){
		colour = c;
		xPosition = x;
		yPosition = y;
	}

	public void changePosition(int x, int y){
		if ((x >= 1) && (y >= 1) && (x < width - 1) && (y < height - 1)){
		xPosition = x;
		yPosition = y;
		getNewX(xPosition);
		getNewY(yPosition);
		}
	}
		
	
	public int getNewX(int x){
		xPosition = x;
		return xPosition;
	}
	
	public int getNewY(int y){
		yPosition = y;
		return yPosition;
	}

	public void changeColour(int c){
		colour = c;
	}

	

	public void draw(GraphicsController mySquare) {
		width = mySquare.getGridWidth();
		height = mySquare.getGridHeight();	
		int a = xPosition - 1;
		int b = yPosition - 1;
		

		if ((xPosition >= 1) && (yPosition >= 1) && (xPosition < width-1) && (yPosition < height-1)){
		mySquare.setGridColour(xPosition, yPosition, colour);
		mySquare.setGridColour(a, b, colour);
		mySquare.setGridColour(a, b + 1, colour);
		mySquare.setGridColour(a, b + 2, colour);
		mySquare.setGridColour(a + 1, b, colour);
		mySquare.setGridColour(a + 1, b + 2 , colour);
		mySquare.setGridColour(a + 2, b, colour);
		mySquare.setGridColour(a + 2, b + 1, colour);
		mySquare.setGridColour(a + 2, b + 2, colour);
			
				 }
	
	}
}
	