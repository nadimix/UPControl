package management;

public class Add_Default_Users implements InterfaceCommand {
	public void execute() {

		Backoffice my_list = new Backoffice();
		ClassUser user = new ClassUser("Manolo", "Escobar", "123456", "70", "1", "Barcelona");
		my_list.add_to_Users_list(user);
		ClassUser user2 = new ClassUser("Francisco", "García", "123456", "78", "2", "Barcelona");
		my_list.add_to_Users_list(user2);
		ClassUser user3 = new ClassUser("Javier", "Lopez", "123456", "7", "3", "Girona");
		my_list.add_to_Users_list(user3);
	}
}
