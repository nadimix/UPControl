package management;

import java.util.ArrayList;

public class Backoffice {
	// esto sería nuestra base de datos, por decirlo de alguna manera. Aqui
	// tenemos dos arrays ("vectores") de las clases usuarios y hotspots

	static public ArrayList<ClassHotSpot> BBDDHotSpots = new ArrayList<ClassHotSpot>();
	static public ArrayList<ClassUser> BBDDUsers = new ArrayList<ClassUser>();

	//acción que usamos para "cargar" la lista de hp
	public ArrayList<ClassHotSpot> getBBDDHotSpots() {
		return BBDDHotSpots;
	}

	public void setBBDDHotSpots(ArrayList<ClassHotSpot> bBDD) {
		BBDDHotSpots = bBDD;
	}

	//acción para añadir hp
	public void add_to_HotSpots_list(ClassHotSpot HP) {

		BBDDHotSpots.add(HP);
	}

	//acción que usamos para "cargar" la lista de users
	public ArrayList<ClassUser> getBBDDUsers() {
		return BBDDUsers;
	}

	public void setBBDDUsers(ArrayList<ClassUser> bBDDUsers) {
		BBDDUsers = bBDDUsers;
	}

	//acción para añadir users
	public void add_to_Users_list(ClassUser User) {

		BBDDUsers.add(User);
	}

}
