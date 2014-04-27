package management;

import java.util.ArrayList;
import java.util.Scanner;

public class ModifyUser implements InterfaceCommand{
	public void execute() {

		Backoffice backoffice = new Backoffice();
		ArrayList<ClassUser> my_list = new ArrayList<ClassUser>();
		Scanner insert = new Scanner(System.in);
		String name_inserted = null;
		String new_name=null;
		Boolean founded=false;
		
		System.out.println("- ModifyUser - Insert the name of the User that you want to modify: ");
		name_inserted = insert.next();

		my_list = backoffice.getBBDDUsers();
		int i = 0;
		while ( my_list.size()>(i)) {
			
			if( !(my_list.get(i).getName().equals(name_inserted)) ){
				i++;
			}else{

			System.out.println("- ModifyUser - Insert the new name:");
			new_name = insert.next();
			my_list.get(i).setName(new_name);
			System.out.println("- ModifyUser - Ok, changes saved.");
			founded = true;
			}
			
		}
		
		if( founded==false){
			System.out.println("- ModifyUser - There is no user with this name.");
		}

	}

}
