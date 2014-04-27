package management;

import java.util.ArrayList;
import java.util.Scanner;

public class FindHotSpot implements InterfaceCommand{
	public void execute(){
		
		Backoffice backoffice = new Backoffice();
		ArrayList<ClassHotSpot> my_list = new ArrayList<ClassHotSpot>();
		Scanner insert = new Scanner(System.in);
		String coordinates_inserted = null;
		Boolean founded = false;
		
		System.out.println("Insert coordinates of HotSpot that you want find :");
		coordinates_inserted= insert.next();
		int  i= 0;
		
		my_list = backoffice.getBBDDHotSpots();
		
		while ( my_list.size()>(i)){
			if (!(my_list.get(i).getCoordinates().equals(coordinates_inserted))){
				i++;
			}else {
				System.out.println(
						  my_list.get(i).getCoordinates() + ", "
						+ my_list.get(i).getDate() + ", "
						+ my_list.get(i).getTime() + ", "
						+ my_list.get(i).getKind() + ", "
						+ my_list.get(i).getDescription() + ".");
				i++;
				founded = true;
			}
			
		}
		if(founded==false){
			System.out.println("Coordenates incorrect");
		}
		
	}
	

}
