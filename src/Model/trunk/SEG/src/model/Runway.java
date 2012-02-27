package model;

public class Runway {

	private String name;
	private int TORA, ASDA, TODA, LDA, displacedThreshold;
	
	//Constructor
	public Runway(String n, int tora, int asda, int toda, int lda, int disThres){
		name = n;
		TORA = tora;
		ASDA = asda;
		TODA = toda;
		LDA = lda;
		displacedThreshold = disThres;
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTORA() {
		return TORA;
	}

	public void setTORA(int tORA) {
		TORA = tORA;
	}

	public int getASDA() {
		return ASDA;
	}

	public void setASDA(int aSDA) {
		ASDA = aSDA;
	}

	public int getTODA() {
		return TODA;
	}

	public void setTODA(int tODA) {
		TODA = tODA;
	}

	public int getLDA() {
		return LDA;
	}

	public void setLDA(int lDA) {
		LDA = lDA;
	}

	public int getDisplacedThreshold() {
		return displacedThreshold;
	}

	public void setDisplacedThreshold(int displacedThreshold) {
		this.displacedThreshold = displacedThreshold;
	}

	
	
	
	
}
