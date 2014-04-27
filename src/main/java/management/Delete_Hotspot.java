package management;

import java.util.ArrayList;
import java.util.Scanner;

public class Delete_Hotspot implements InterfaceCommand {

	public void execute() {
		Backoffice backoffice = new Backoffice();
		ArrayList<ClassHotSpot> my_list = new ArrayList<ClassHotSpot>();
		Scanner insert = new Scanner(System.in);
		String coordinates_inserted = null;

		System.out
				.println("Insert the coordinates of the hotspot you want to delete: ");
		coordinates_inserted = insert.next();

		Boolean founded=false;
		my_list = backoffice.getBBDDHotSpots();
		int i = 0;
		while (my_list.size() > (i)) {
			if (!(my_list.get(i).getCoordinates().equals(coordinates_inserted))) {
				i++;
			} else {
				my_list.remove(i);
				System.out.println("- DeleteHotSpot - Ok, changes saved.");
				founded = true;
			}
		}

		if (founded == false) 
			System.out
					.println("There is no HotSpot with this coordinates.");
		
	}

}
