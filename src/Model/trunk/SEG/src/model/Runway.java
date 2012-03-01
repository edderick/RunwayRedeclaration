package model;

public class Runway {

	private String name;
	private int[] TORA, ASDA, TODA, LDA, displacedThreshold;
	
	//Constructor
	public Runway(String n, int tora, int asda, int toda, int lda, int disThres){
		name = n;
		TORA = new int[2];//tora;
		TORA[0] = tora;
		TORA[1] = tora;
		ASDA = new int[2];//asda;
		ASDA[0] = asda;
		ASDA[1] = asda;
		TODA = new int[2];//toda;
		TODA[0] = toda;
		TODA[1] = toda;
		LDA = new int[2];//lda;
		LDA[0] = lda;
		LDA[1] = lda;
		displacedThreshold = new int[2];//disThres;
		displacedThreshold[0] = disThres;
		displacedThreshold[1] = disThres;
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTORA(int option) {
		return TORA[option];
	}

	public void setTORA(int option, int tORA) {
		TORA[option] = tORA;
	}

	public int getASDA(int option) {
		return ASDA[option];
	}

	public void setASDA(int option, int aSDA) {
		ASDA[option] = aSDA;
	}

	public int getTODA(int option) {
		return TODA[option];
	}

	public void setTODA(int option, int tODA) {
		TODA[option] = tODA;
	}

	public int getLDA(int option) {
		return LDA[option];
	}

	public void setLDA(int option, int lDA) {
		LDA[option] = lDA;
	}

	public int getDisplacedThreshold(int option) {
		return displacedThreshold[option];
	}

	public void setDisplacedThreshold(int option, int displacedThreshold) {
		this.displacedThreshold[option] = displacedThreshold;
	}

	
	
	
	
}
