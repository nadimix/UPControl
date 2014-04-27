package management;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class CreateHotSpot implements InterfaceCommand {
	private Scanner insert;

	public void execute() {
		
		Backoffice lista = new Backoffice();
		insert = new Scanner(System.in);
		Calendar calendar = Calendar.getInstance();
		
		
		String coordinates = null;
		String time= null;
		String date = null;
		String kind = null;
		String description = null;
		String time1= null;
		String time2= null;
		
		
	
		System.out.println("CreateHotSpot - To create a new HotsPot, you must fill in the following fields");
		System.out.println("Cordenates: ");
		coordinates = insert.next();
		System.out.println("kind of HotsPots: ");
		kind = insert.next();
		System.out.println("Date: ");
		date = insert.next();
		System.out.println("Short description: ");
		description = insert.next();
		time1=Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		time2=Integer.toString(calendar.get(Calendar.MINUTE));
		
		
		ClassHotSpot HotsPot = new ClassHotSpot(coordinates,time1+":"+time2, date, kind, description);
		
		lista.add_to_HotSpots_list(HotsPot);
		
		
	}



	}




