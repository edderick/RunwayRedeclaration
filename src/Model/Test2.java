package Model;

import java.util.*;

public class Test2 {
	
	public static void main(String[] args) throws Exception {
	    @SuppressWarnings("unused")
		Scanner in = new Scanner(System.in);
	    
	    /*
	     * Testing for AddressBook and Contacts
	     */
	    /*
	    AddressBook ab = new AddressBook();
	    Contact c1 = new Contact("Kelvin", "Chan", "ycc1g11@soton.ac.uk");
	    System.out.println(c1);
	    Contact c2 = new Contact("Brian", "Yu", "by1g10@soton.ac.uk");
	    System.out.println(c2);
	    Contact c3 = new Contact("Cathy", "Yu", "cy1g10@soton.ac.uk");
	    System.out.println(c3);
	    
	    if(ab.addContact(c1)){
	    	System.out.println("Contact added.");
	    }else{
	    	System.out.println("Duplicated contact found.");
	    }
	    if(ab.addContact(c2)){
	    	System.out.println("Contact added.");
	    }else{
	    	System.out.println("Duplicated contact found.");
	    }
	    if(ab.addContact(c3)){
	    	System.out.println("Contact added.");
	    }else{
	    	System.out.println("Duplicated contact found.");
	    }
	    
	    in.nextLine();

	    ArrayList<Contact> l1 = ab.searchContactsByFirstName("Kelvin");
	    for(Contact cl1 : l1){
	    	System.out.println(cl1);
	    }
	    
	    ArrayList<Contact> l2 = ab.searchContactsByLastName("Yu");
	    for(Contact cl2 : l2){
	    	System.out.println(cl2);
	    }
	    
	    ArrayList<Contact> l3 = ab.searchContactsByLastName("ABC");
	    for(Contact cl2 : l3){
	    	System.out.println(cl2);
	    }
	    
	    in.nextLine();
	    */
	    
	    /*
	     * Testing for Obstacle
	     */
	    Obstacle ob = new Obstacle("Boeing 777", 10);
	    
	    /*
	    
	    System.out.println("Obstacle : "+ob.getName());
	    System.out.println("Height of the obstacle : "+ob.getHeight());
	    */
	    
        Obstacle ob2 = new Obstacle("Boeing 757", 4);
        
        Obstacle ob3 = new Obstacle("Boeing 756", 5);
        
        Obstacle ob4 = new Obstacle("Boeing 756", 25);
        
	    /*
	    System.out.println("Obstacle : "+ob2.getName());
	    System.out.println("Height of the obstacle : "+ob2.getHeight());
	    
        Obstacle ob3 = new Obstacle(null, 30);
	    
	    System.out.println("Obstacle : "+ob3.getName());
	    System.out.println("Height of the obstacle : "+ob3.getHeight());
	    
        Obstacle ob4 = new Obstacle(null, -5000);
	    
	    System.out.println("Obstacle : "+ob4.getName());
	    System.out.println("Height of the obstacle : "+ob4.getHeight());
	    
	    ob.setHeight(-9999);
	    ob.setLength(-500);
	    ob.setWidth(-1000);
	    ob.setName(null);
	    
	    System.out.println("Obstacle : "+ob.getName());
	    System.out.println("Height of the obstacle : "+ob.getHeight());
	    System.out.println("Length of the obstacle : "+ob.getLength());
	    System.out.println("Width of the obstacle : "+ob.getWidth());
	    
	    */
	    
	    /*
	     * Testing for PhysicalRunway
	     */
		Airport a = new Airport("Heathrow");

		Runway r1_1, r1_2;
		Runway r2_1, r2_2;
		r1_1 = new Runway("09L", 3902, 3902, 3902, 3595, 306);
		r1_2 = new Runway("27R", 3884, 3884, 3962, 3884, 0);
		r2_1 = new Runway("09R", 3658, 3658, 3658, 3353, 307);
		r2_2 = new Runway("27L", 3658, 3658, 3658, 3658, 0);
		
		PhysicalRunway pr1, pr2;
		pr1 = new PhysicalRunway("09L-27R", r1_1, r1_2);
		pr2 = new PhysicalRunway("09R-27L", r2_1, r2_2);
		a.addPhysicalRunway(pr1);
		a.addPhysicalRunway(pr2);
		
		pr1.placeNewObstacle(ob, 125, 20, pr1.getRunway(0).getName());
		System.out.println(pr1.toDetails("09L"));
		System.out.println(pr1.toDetails("27R"));
		
		pr1.placeNewObstacle(ob2, 500, 0, pr1.getRunway(1).getName());
		System.out.println(pr1.toDetails("27R"));
		System.out.println(pr1.toDetails("09L"));
		
		pr2.placeNewObstacle(ob3, 0, 5, pr2.getRunway(0).getName());
		System.out.println(pr2.toCalculation("09R"));
		System.out.println(pr2.toCalculation("27L"));
		
		pr2.placeNewObstacle(ob4, 250, 190, pr2.getRunway(1).getName());
		System.out.println(pr2.toDetails("27L"));
		System.out.println(pr2.toCalculation("27L"));
		System.out.println(pr2.toDetails("09R"));
		
		/*
		pr1.removeObstacleAndReset();
		System.out.println(pr1.toCalculation("09L-27R"));
		pr1.placeNewObstacle(ob, 500, 180, pr1.getRunway(1).getName());
		System.out.println(pr1.toCalculation("09L-27R"));
		
		pr1.placeNewObstacle(ob2, 500, 30, pr1.getRunway(1).getName());
		System.out.println(pr1.toCalculation(pr1.getRunway(1).getName()));
		pr1.removeObstacleAndReset();
		System.out.println(pr1.toCalculation(pr1.getRunway(1).getName()));
		*/
		
		/*
		PhysicalRunway copyOfpr1 = (PhysicalRunway) a.getPhysicalRunways().get(0);
	    copyOfpr1.placeNewObstacle(ob, 500, 30, copyOfpr1.getRunway(1).getName());
	    
	    System.out.println("Placing obstacle on "+copyOfpr1.getId());
	    System.out.println("Obstacle : "+ob.getName());
	    System.out.println("Height of the obstacle : "+ob.getHeight());
	    System.out.println("Close to threshold of  : "+copyOfpr1.closeTo().getName());
	    System.out.println("Distance away from the threshold : "+copyOfpr1.getDistanceAwayFromThreshold());
	    System.out.println();
	    
	    Runway one, two;
	    one = copyOfpr1.getRunway(0);
	    two = copyOfpr1.getRunway(1);
	    
	    System.out.println("Physical Runway Name: "+copyOfpr1.getId());
	    System.out.println("Runway: "+one.getName());
	    System.out.println("Original TORA: "+one.getTODA(0)+"    New TORA: "+one.getTORA(1));
	    System.out.println("Original ASDA: "+one.getASDA(0)+"    New ASDA: "+one.getASDA(1));
	    System.out.println("Original TODA: "+one.getTODA(0)+"    New TODA: "+one.getTODA(1));
	    System.out.println("Original LDA : "+one.getLDA(0)+"    New LDA: "+one.getLDA(1));
	    
	    System.out.println("Runway: "+two.getName());
	    System.out.println("Original TORA: "+two.getTODA(0)+"    New TORA: "+two.getTORA(1));
	    System.out.println("Original ASDA: "+two.getASDA(0)+"    New ASDA: "+two.getASDA(1));
	    System.out.println("Original TODA: "+two.getTODA(0)+"    New TODA: "+two.getTODA(1));
	    System.out.println("Original LDA : "+two.getLDA(0)+"    New LDA: "+two.getLDA(1));
	    
	    in.nextLine();
	    
	    System.out.println();
	    System.out.println("Calculation on Runway: "+two.getName());
	    System.out.println(copyOfpr1.toCalculation(two.getName()));
	    
	    in.nextLine();
	    int angle, blast;
	    System.out.print("New Angle of Slope : ");
	    angle = Integer.parseInt(in.nextLine());
	    System.out.print("New Blast Allowance (300-500) : ");
	    blast = Integer.parseInt(in.nextLine());
		
	    System.out.println("Set angle of slope = "+angle);
	    System.out.println("Set blast allowance = "+blast);
	    copyOfpr1.setAngleOfSlope(angle);
	    copyOfpr1.setBlastAllowance(blast);
	    
	    System.out.println();
	    System.out.println("Runway: "+two.getName());
	    System.out.println("Original TORA: "+two.getTODA(0)+"    New TORA: "+two.getTORA(1));
	    System.out.println("Original ASDA: "+two.getASDA(0)+"    New ASDA: "+two.getASDA(1));
	    System.out.println("Original TODA: "+two.getTODA(0)+"    New TODA: "+two.getTODA(1));
	    System.out.println("Original LDA : "+two.getLDA(0)+"    New LDA: "+two.getLDA(1));
	    
	    System.out.println();
	    System.out.println(copyOfpr1.toCalculation(two.getName()));
	    
	    in.nextLine();
	    
	    System.out.println();
	    System.out.println("Reset parameters");
	    copyOfpr1.defaultValues();
	    
	    in.nextLine();
	    
	    System.out.println();
	    System.out.println("Runway: "+two.getName());
	    System.out.println("Original TORA: "+two.getTODA(0)+"    New TORA: "+two.getTORA(1));
	    System.out.println("Original ASDA: "+two.getASDA(0)+"    New ASDA: "+two.getASDA(1));
	    System.out.println("Original TODA: "+two.getTODA(0)+"    New TODA: "+two.getTODA(1));
	    System.out.println("Original LDA : "+two.getLDA(0)+"    New LDA: "+two.getLDA(1));
	    
	    System.out.println();
	    System.out.println(copyOfpr1.toCalculation(two.getName()));
	    
	    in.nextLine();
	    int stopway, distanceAwayFromThreshold;
	    System.out.print("New distance away from threshold : ");
	    distanceAwayFromThreshold = Integer.parseInt(in.nextLine());
	    System.out.print("New stopway distance : ");
	    stopway = Integer.parseInt(in.nextLine());
		
	    System.out.println("Set distance away from threshold = "+distanceAwayFromThreshold);
	    System.out.println("Set stopway = "+stopway);
	    copyOfpr1.setDistanceAwayFromThreshold(distanceAwayFromThreshold);
	    copyOfpr1.setStopway(stopway);
	    
	    
	    System.out.println();
	    System.out.println("Runway: "+two.getName());
	    System.out.println("Original TORA: "+two.getTODA(0)+"    New TORA: "+two.getTORA(1));
	    System.out.println("Original ASDA: "+two.getASDA(0)+"    New ASDA: "+two.getASDA(1));
	    System.out.println("Original TODA: "+two.getTODA(0)+"    New TODA: "+two.getTODA(1));
	    System.out.println("Original LDA : "+two.getLDA(0)+"    New LDA: "+two.getLDA(1));
	    
	    System.out.println();
	    System.out.println(copyOfpr1.toCalculation(two.getName()));
	    
	    in.close();
	    
		/*
		String host = "smtp.gmail.com";
	    String from = "kelvin.ycchan";
	    String pass = "YNWAjft96";
	    Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", "true"); // added this line
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", pass);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");

	    String[] to = {"chaklfc25@gmail.com"}; // added this line

	    Session session = Session.getDefaultInstance(props, null);
	    MimeMessage message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(from));

	    InternetAddress[] toAddress = new InternetAddress[to.length];

	    // To get the array of addresses
	    for( int i=0; i < to.length; i++ ) { // changed from a while loop
	        toAddress[i] = new InternetAddress(to[i]);
	    }
	    System.out.println(Message.RecipientType.TO);

	    for( int i=0; i < toAddress.length; i++) { // changed from a while loop
	        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	    }
	    message.setSubject("sending in a group");
	    message.setText("Welcome to JavaMail");
	    Transport transport = session.getTransport("smtp");
	    transport.connect(host, from, pass);
	    transport.sendMessage(message, message.getAllRecipients());
	    transport.close();
	    */
	}

}
