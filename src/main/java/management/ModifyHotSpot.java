package management;

import java.util.ArrayList;
import java.util.Scanner;

public class ModifyHotSpot implements InterfaceCommand {
	public void execute() {

		Backoffice backoffice = new Backoffice();
		ArrayList<ClassHotSpot> my_list = new ArrayList<ClassHotSpot>();
		Scanner insert = new Scanner(System.in);
		String coordinates_inserted = null;
		String new_coordinates=null;
		Boolean founded=false;
		
		System.out.println("- ModifyHotSpot - Insert the coordinates of the HotSpot that you want to modify: ");
		coordinates_inserted = insert.next();

		my_list = backoffice.getBBDDHotSpots();
		int i = 0;
		while ( my_list.size()>(i)) {
			if( !(my_list.get(i).getCoordinates().equals(coordinates_inserted)) ){
				i++;
			}else{

			System.out.println("- ModifyHotSpot - Insert the new coordinates:");
			new_coordinates = insert.next();
			my_list.get(i).setCoordinates(new_coordinates);
			System.out.println("- ModifyHotSpot - Ok, changes saved.");
			founded=true;
			}
		}
		
		if(founded==false){
		System.out.println("- ModifyHotSpot - There is no HotSpot with this coordinates.");}
	}

}
