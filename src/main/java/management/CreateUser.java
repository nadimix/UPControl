package management;

import java.util.ArrayList;
import java.util.Scanner;


public class CreateUser implements InterfaceCommand {
	private Scanner insert;

	public void execute() {
		
		Backoffice lista = new Backoffice();
		insert = new Scanner(System.in);
		
	 String name = null;
	 String surname = null;
	 String password = null;
	 String age = null;
	 String id = null;
	 String city = null;
	 
	 System.out.println("CreateUser - To create a new User, you must fill in the following fields");
		System.out.println("Name: ");
		name = insert.next();
		System.out.println("Surname: ");
		surname = insert.next();
		System.out.println("Password: ");
		password = insert.next();
		System.out.println("Age: ");
		age = insert.next();
		System.out.println("Id: ");
		id = insert.next();
		System.out.println("City: ");
		city = insert.next();
		
		ClassUser User=new ClassUser(name,surname,password,age,id, city);

		
		lista.add_to_Users_list(User);
		
}
}