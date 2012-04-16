package Model;

public class Runway {

	private String name;
	private double[] TORA, ASDA, TODA, LDA, displacedThreshold;
	
	//Constructor
	public Runway(String n, double tora, double asda, double toda, double lda, double disThres){
		name = n;
		TORA = new double[2];//tora;
		TORA[0] = tora;
		TORA[1] = tora;
		ASDA = new double[2];//asda;
		ASDA[0] = asda;
		ASDA[1] = asda;
		TODA = new double[2];//toda;
		TODA[0] = toda;
		TODA[1] = toda;
		LDA = new double[2];//lda;
		LDA[0] = lda;
		LDA[1] = lda;
		displacedThreshold = new double[2];//disThres;
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

	public double getTORA(int option) {
		return TORA[option];
	}

	public void setTORA(int option, double tORA) {
		TORA[option] = tORA;
	}

	public double getASDA(int option) {
		return ASDA[option];
	}

	public void setASDA(int option, double aSDA) {
		ASDA[option] = aSDA;
	}

	public double getTODA(int option) {
		return TODA[option];
	}

	public void setTODA(int option, double tODA) {
		TODA[option] = tODA;
	}

	public double getLDA(int option) {
		return LDA[option];
	}

	public void setLDA(int option, double lDA) {
		LDA[option] = lDA;
	}

	public double getDisplacedThreshold(int option) {
		return displacedThreshold[option];
	}

	public void setDisplacedThreshold(int option, double displacedThreshold) {
		this.displacedThreshold[option] = displacedThreshold;
	}

	
	
	
	
}
