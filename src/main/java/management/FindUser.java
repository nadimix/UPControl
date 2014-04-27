package management;

import java.util.ArrayList;
import java.util.Scanner;

public class FindUser implements InterfaceCommand{

	public void  execute(){
		
		Backoffice backoffice = new Backoffice();
		ArrayList<ClassUser> my_list = new ArrayList<ClassUser>();
		Scanner insert = new Scanner(System.in);
		String name_inserted = null;
		Boolean founded = false;
		
		System.out.println("Insert User name that you want find :");

		name_inserted= insert.next();
		
		int  i= 0;
		
		my_list = backoffice.getBBDDUsers();
		
		while ( my_list.size()>(i)){
			if (!(my_list.get(i).getName().equals(name_inserted))){
				i++;
			}else {
				System.out.println( "The user data are the next:");
				System.out.println(my_list.get(i).getId() + ", "
						+ my_list.get(i).getName() + ", "
						+ my_list.get(i).getSurname() + ", "
						+ my_list.get(i).getAge() + ", "
						+ my_list.get(i).getCity() + ", "
						+ my_list.get(i).getPassword() + ".");
				
				founded = true;
				i++;
			}
			
		}
		
		if(founded==false){
			System.out.println("User name incorrect");
		}
		
	}
	

}
