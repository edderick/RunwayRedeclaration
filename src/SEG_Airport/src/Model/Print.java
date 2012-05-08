package Model;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * This class manages the print process or job.
 * It creates an object that will hold all print parameters.
 * A page is printed by first instantiating this class, then calling the print() method.
 * @author Oscar
 */

public class Print {
	
	String calculations;
	
	/**
	 * Default constructor for Print class
	 */
	public Print(){}

	
	/**
	 * Creates the object that will hold all print parameters.
	 * @param String with the text to be printed to paper or pdf.
	 */
	public void print(String calculations){
		this.calculations = calculations;
		
		PrinterJob job = PrinterJob.getPrinterJob();

		/*
		String p = "Calcuations on runway 09L:\n"
				+ "Obstacle name: Obstacle 1\n"
				+ "The obstacle is closer to 27R's threshold\n"
				+ "Obstacle height: 10.0m\n"
				+ "Distance away from threshold: 0.0m\n"
				+ "Blast allowance: 300.0m\n"
				+ "Displaced threshold: 0.0m\n"
				+ "Angle of slope: 50.0\n"
				+ "RESA: 240.0m\n"
				+ "Stopway: 60.0m\n"
				+ "\n"
				+ "--Any negative result will be assigned as zero--\n"
				+ "New TORA : 3902.0 - 0.0 - (10.0 * 50.0) - 60.0 - 0.0 = 3342.0m\n"
				+ "New TODA : 3902.0 - 0.0 - (10.0 * 50.0) - 60.0 - 0.0 = 3342.0m\n"
				+ "New ASDA : 3902.0 - 0.0 - (10.0 * 50.0) - 60.0 - 0.0 = 3342.0m\n"
				+ "New LDA  : 3595.0 - 0.0 - 0.0 - 240.0 - 60.0 = 3295.0m\n"
				+ "";
		*/

		job.setPrintable(new PrintObject(calculations));

		if (job.printDialog()) {
			
			try {
				job.print();
			} catch (PrinterException e) {
				System.out.println(e);
			}
		}
	}

}
