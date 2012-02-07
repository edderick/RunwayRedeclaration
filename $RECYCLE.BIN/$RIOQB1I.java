import java.util.Random;


public class DemoWindow extends WindowController {
	private int lastButtonPressed;
	private int snailPositionX = 0;
	private int snailPositionY = 0;
	private int colour;
	private int randomColour = 0;
	private int gh;
	private int gw;
	int gridHeight = getGridHeight();
	int gridWidth = getGridWidth();


	Random randomColourMaker = new Random();
	Random gridNumberMaker = new Random();
	SquareDrawableObject mySquare = new SquareDrawableObject(0,0,0);
	SquareDrawableObject movingSquare = new SquareDrawableObject(0, 0, 0); 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DemoWindow myWindow = new DemoWindow("ba5g10");
		myWindow.setButtonText(0, "A") ;
		myWindow.setButtonText(1, "B") ;
		myWindow.setButtonText(2, "C") ;
		myWindow.setButtonText(3, "D") ;
		myWindow.setBackgroundColour(6);
	}

	public DemoWindow(java.lang.String windowName){
		super(windowName);
	}

	@Override
	protected void buttonPressed(int id){
		/** This method uses the id of the button pressed so that we can decide what action to use as a result.
		 * 
		 * 
		 */
		if (id==0){
			actionsForIDEquals0();
		}
		
		if (id==1){
			actionsForIDEquals1();
		}
		
		if (id ==2){
			actionsForIDEquals2();
		}
		
		if (id == 3){
			actionsforIDEquals3();
		}
		
		lastButtonPressed(id);
	}
			
		
		 
		
	private void actionsForIDEquals0(){
			System.out.println("A");
			
			
			for (int idx = 1; idx <= 1; ++idx){
			      randomColour = randomColourMaker.nextInt(9);
			     }
					colour = randomColour;
			for (gw = 0; gw < gridWidth ; gw++){
				for (gh = 0; gh < gridHeight; gh++){
					setGridColour(gw, gh, colour);
			}
			}
			
			switch (colour) {
            case 0:  System.out.println("Black"); break;
            case 1:  System.out.println("White"); break;
            case 2:  System.out.println("Red"); break;
            case 3:  System.out.println("Blue"); break;
            case 4:  System.out.println("Green"); break;
            case 5:  System.out.println("Orange"); break;
            case 6:  System.out.println("Pink"); break;
            case 7:  System.out.println("Yellow"); break;
            case 8:  System.out.println("Purple"); break;
            
			}
		
	}
	
	private void actionsForIDEquals1(){
			System.out.println("B");
			if (lastButtonPressed!=1)
			{
				for (gw = 0; gw < gridWidth ; gw++){
					for (gh = 0; gh < gridHeight; gh++){
					 setGridColour(gw, gh, 0);
			}
				}
				snailPositionX = 15;
				snailPositionY = 15;
				}
		
		
			
			Random snailPositionChangeX = new Random();
	    	Random snailPositionChangeY = new Random();
			 
		if (lastButtonPressed == 1){

				
	            /** nextInt(3) produces a random number in the range 0-2
	             * - 2 decreases the random number by 2 so it is in the range of -1 and 1
	             */
				setGridColour(snailPositionX, snailPositionY, 7);
				
				/** if code to ensure the snail is kept within the boundary of the grid
				 */
				if (snailPositionX >= 1 && snailPositionX <= gridWidth - 2 && snailPositionY >=1 && snailPositionY <= gridHeight - 2){
	            snailPositionX = snailPositionX + snailPositionChangeX.nextInt(3) - 1;
	            snailPositionY = snailPositionY + snailPositionChangeY.nextInt(3) - 1;
	           	}
				
				else if (snailPositionX == 0 && snailPositionY >=1 && snailPositionY <= gridHeight - 2){
					snailPositionX = snailPositionChangeX.nextInt(2);
		            snailPositionY = snailPositionY + snailPositionChangeY.nextInt(3) - 1;
				}
				
				else if (snailPositionX >= 1 && snailPositionX <= gridWidth - 2 && snailPositionY == 0){
					snailPositionX = snailPositionX + snailPositionChangeX.nextInt(3) - 1;
		            snailPositionY = snailPositionChangeY.nextInt(2);
				}
				
				else if (snailPositionX == gridWidth - 1 && snailPositionY >=1 && snailPositionY <= gridHeight -1){
					snailPositionX = gridWidth - snailPositionChangeX.nextInt(2) - 1;
		            snailPositionY = snailPositionY + snailPositionChangeY.nextInt(3) - 1;
				}
				
				else if (snailPositionX >= 1 && snailPositionX <= gridWidth - 2 && snailPositionY == gridHeight - 1){
					snailPositionX = snailPositionX + snailPositionChangeX.nextInt(3) - 1;
		            snailPositionY = snailPositionY - snailPositionChangeY.nextInt(2) - 1;
				}
				
				else if (snailPositionX == 0 && snailPositionY == 0 ){
		            snailPositionX = snailPositionChangeX.nextInt(2);
		            snailPositionY = snailPositionChangeY.nextInt(2);
		           	}
				else if (snailPositionX == gridWidth - 1 && snailPositionY == gridHeight - 1){
		            snailPositionX = gridWidth + snailPositionChangeX.nextInt(2) - 2;
		            snailPositionY = gridHeight + snailPositionChangeY.nextInt(2) - 2;
		           	}
				else if (snailPositionX == 0 && snailPositionY == gridHeight - 1){
		            snailPositionX = snailPositionChangeX.nextInt(2);
		            snailPositionY = snailPositionY - snailPositionChangeY.nextInt(2) - 1;
		           	}
				else if (snailPositionX == gridWidth && snailPositionY == 0){
		            snailPositionX = gridWidth - snailPositionChangeX.nextInt(2) - 1;
		            snailPositionY = snailPositionChangeY.nextInt(2);
		           	}
			
			
				
				setGridColour(snailPositionX, snailPositionY, 1);
	            getSnailPositionX(snailPositionX);
		        getSnailPositionY(snailPositionY);
		
		}
		}
		
	private void actionsForIDEquals2(){
			System.out.println("C");
			
				
				 randomColour = randomColourMaker.nextInt(8) + 1;
			     colour = randomColour;
				 
				 
			if (lastButtonPressed!=2){
				for (gw = 0; gw < gridWidth ; gw++){
					for (gh = 0; gh < gridHeight; gh++){
					 setGridColour(gw, gh, 0);
					}
					}
				mySquare.changeColour(colour);
				mySquare.changePosition(gridWidth / 2,gridHeight / 2);
				this.draw(mySquare);
						
					} 
			
			else {				
				mySquare.changeColour(colour);
				mySquare.changePosition(gridNumberMaker.nextInt(getGridWidth()+1), gridNumberMaker.nextInt(getGridHeight()+1));
					this.draw(mySquare);
					}	
		 
		 }
		 
	
	private void actionsforIDEquals3(){
				System.out.println("D");
				for (gw = 0; gw < gridWidth ; gw++){
					for (gh = 0; gh < gridHeight; gh++){
					 setGridColour(gw, gh, 0);
					}
					}

				randomColour = randomColourMaker.nextInt(8) + 1;
			    colour = randomColour;
				
				movingSquare.xPosition = getGridWidth() / 2;
				movingSquare.yPosition = getGridHeight() / 2;
				movingSquare.changeColour(colour);
				this.draw(movingSquare);
	}
		

	public int getSnailPositionX (int x){
		snailPositionX = x;
		return snailPositionX;
	}
	
	public int getSnailPositionY (int y){
		snailPositionY = y;
		return snailPositionY;
	}
	
	public int lastButtonPressed(int id){
		lastButtonPressed = id;
		return lastButtonPressed;
		
	}
	
	
	public void setBackgroundColour(int colour){
		int gh;
		int gw;
		int gridHeight = getGridHeight();
		int gridWidth = getGridWidth();
		System.out.println(getGridHeight());
		Random randomColourMaker = new Random();
		for (int idx = 1; idx <= 1; ++idx){
		      int randomColour = randomColourMaker.nextInt(9);
		      colour = randomColour;
		    }
		    
		  
		
		for (gw = 0; gw < gridWidth ; gw++){
		for (gh = 0; gh < gridHeight; gh++){
		 setGridColour(gw, gh, colour);
		}
		}
		}



	@Override
	protected void keyPressed(Key arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyReleased(Key arg0) {
		// TODO Auto-generated method stub
		
	}

	protected void update() {
		
			if (lastButtonPressed == 3){
				
				
			for (gw = 0; gw < gridWidth ; gw++){
					for (gh = 0; gh < gridHeight; gh++){
					 setGridColour(gw, gh, 0);
					}}
				
			Random changeMaker = new Random();
			int randomChange = changeMaker.nextInt(4);
			
			if (randomChange == 0){
			movingSquare.xPosition = movingSquare.xPosition + 1;
			}
			if (randomChange == 1){
			movingSquare.xPosition = movingSquare.xPosition-1;
				}
			if (randomChange == 2){
			movingSquare.yPosition = movingSquare.yPosition + 1;
				}
			if (randomChange == 3){
			movingSquare.yPosition = movingSquare.yPosition - 1;
				}
			this.draw(movingSquare);
	
}
	}
}






