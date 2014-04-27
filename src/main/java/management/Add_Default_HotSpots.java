package management;

public class Add_Default_HotSpots implements InterfaceCommand {
	
	public void execute() {
		ClassHotSpot HotSpot = new ClassHotSpot("001001", "19:45", "17/10/2013", "Radar Móvil", "Ford Focus C-Max matricula B0598LZ");
		Backoffice my_list = new Backoffice();
		my_list.add_to_HotSpots_list(HotSpot );
		ClassHotSpot HotSpot2 = new ClassHotSpot("002002", "08:45", "19/10/2013", "Control de alcoholemia", "Policias muy chungos");
		my_list.add_to_HotSpots_list(HotSpot2);
		ClassHotSpot HotSpot3 = new ClassHotSpot("003003", "09:45", "16/10/2013", "Accidente", "Camión vs. Peugeot 206");
		my_list.add_to_HotSpots_list(HotSpot3);
		
	}
}
