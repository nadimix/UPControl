package management;

import java.util.ArrayList;

public class ReadUsers implements InterfaceCommand {
	public void execute() {
		System.out
				.println("- - - Format of reading: id, name, surname, age, city, password");
		Backoffice backoffice = new Backoffice();
		ArrayList<ClassUser> my_list = new ArrayList<ClassUser>();
		my_list = backoffice.getBBDDUsers();
		int i = 0;
		while (my_list.size() > i) {

			System.out.println("Read User" + i + ": "
					+ my_list.get(i).getId() + ", "
					+ my_list.get(i).getName() + ", "
					+ my_list.get(i).getSurname() + ", "
					+ my_list.get(i).getAge() + ", "
					+ my_list.get(i).getCity() + ", "
					+ my_list.get(i).getPassword() + ".");
			i++;

		}
	}
}
