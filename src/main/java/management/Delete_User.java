package management;

import java.util.ArrayList;
import java.util.Scanner;

public class Delete_User implements InterfaceCommand {

	public void execute() {
		Backoffice backoffice = new Backoffice();
		ArrayList<ClassUser> my_list = new ArrayList<ClassUser>();
		Scanner insert = new Scanner(System.in);
		String name_inserted = null;
		Boolean founded = false;

		System.out
				.println("Insert the name of the user that you want to delete: ");
		name_inserted = insert.next();

		my_list = backoffice.getBBDDUsers();
		int i = 0;
		while (my_list.size() > (i)) {

			if (!(my_list.get(i).getName().equals(name_inserted))) {
				i++;
			} else {
				my_list.remove(i);
				System.out.println("Ok, changes saved.");
				founded = true;
			}

		}

		if (founded == false)
			System.out.println("There is no user with this name.");
	}

}
