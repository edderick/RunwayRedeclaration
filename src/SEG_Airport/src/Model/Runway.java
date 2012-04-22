package Model;

/**
 * Represents a declared runway
 * @author Oscar and Kelvin
 */
public class Runway {

	public static int DEFAULT = 0;
	public static int REDECLARED = 1;
	
	private String name;
	private double[] TORA, ASDA, TODA, LDA, displacedThreshold;
	
	/**
	 * Default constructor for runway
	 * @param name Name of runway
	 * @param tora Take-Off Run Available
	 * @param asda Accelerate Stop Distance Available
	 * @param toda Take-Off Distance Available
	 * @param lda Landing Distance Available
	 * @param displacedThreshold The displaced threshold
	 */
	public Runway(String name, double tora, double asda, double toda, double lda, double displacedThreshold){
		this.name = name;
		
		TORA = new double[2];
		TORA[DEFAULT] = tora;
		TORA[REDECLARED] = tora;
		
		ASDA = new double[2];
		ASDA[DEFAULT] = asda;
		ASDA[REDECLARED] = asda;
		
		TODA = new double[2];
		TODA[DEFAULT] = toda;
		TODA[REDECLARED] = toda;
		
		LDA = new double[2];
		LDA[DEFAULT] = lda;
		LDA[REDECLARED] = lda;
		
		this.displacedThreshold = new double[2];
		this.displacedThreshold[DEFAULT] = displacedThreshold;
		this.displacedThreshold[REDECLARED] = displacedThreshold;
	}
	
	/**
	 * @return The name of the runway
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The new name of the runway
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param option Runway.DEFAULT or Runway.REDECLARED
	 * @return Value of TORA
	 */
	public double getTORA(int option) {
		return TORA[option];
	}

	/**
	 * @param option Runway.DEFAULT or Runway.REDECLARED
	 * @param tORA New value for TORA
	 */
	public void setTORA(int option, double tORA) {
		TORA[option] = tORA;
	}

	/**
	 * @param option Runway.DEFAULT or Runway.REDECLARED
	 * @return Value of ASDA
	 */
	public double getASDA(int option) {
		return ASDA[option];
	}

	/**
	 * @param option Runway.DEFAULT or Runway.REDECLARED
	 * @param aSDA New value of ASDA
	 */
	public void setASDA(int option, double aSDA) {
		ASDA[option] = aSDA;
	}

	/**
	 * @param option  Runway.DEFAULT or Runway.REDECLARED
	 * @return Value of TODA
	 */
	public double getTODA(int option) {
		return TODA[option];
	}

	/**
	 * @param option Runway.DEFAULT or Runway.REDECLARED
	 * @param tODA New value for TODA
	 */
	public void setTODA(int option, double tODA) {
		TODA[option] = tODA;
	}

	/**
	 * @param option Runway.DEFAULT or Runway.REDECLARED
	 * @return Value of LDA
	 */
	public double getLDA(int option) {
		return LDA[option];
	}

	/**
	 * @param option Runway.DEFAULT or Runway.REDECLARED
	 * @param lDA The new value for LDA
	 */
	public void setLDA(int option, double lDA) {
		LDA[option] = lDA;
	}

	/**
	 * @param option Runway.DEFAULT or Runway.REDECLARED
	 * @return The value of the displacement threshold
	 */
	public double getDisplacedThreshold(int option) {
		return displacedThreshold[option];
	}

	/**
	 * @param option Runway.DEFAULT or Runway.REDECLARED
	 * @param displacedThreshold The new value for displacement threshold
	 */
	public void setDisplacedThreshold(int option, double displacedThreshold) {
		this.displacedThreshold[option] = displacedThreshold;
	}
}
