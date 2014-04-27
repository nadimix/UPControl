package management;

import java.util.ArrayList;

import org.omg.CORBA.ExceptionList;

public class ReadHotSpots implements InterfaceCommand {
	public void execute() {
		System.out
				.println("- - - Format of reading: coordinates, date, time, kind, description");
		Backoffice backoffice = new Backoffice();
		ArrayList<ClassHotSpot> my_list = new ArrayList<ClassHotSpot>();
		my_list = backoffice.getBBDDHotSpots();
		int i = 0;
		while (my_list.size() > i) {

			System.out.println("Read HostPot" + i + ": "
					+ my_list.get(i).getCoordinates() + ", "
					+ my_list.get(i).getDate() + ", "
					+ my_list.get(i).getTime() + ", "
					+ my_list.get(i).getKind() + ", "
					+ my_list.get(i).getDescription() + ".");
			i++;

		}
	}
}
