package hibernate.main;

import hibernate.manager.HotSpotManager;
import hibernate.manager.UserManager;

import java.util.List;

import model.HotSpot;
import model.User;
import util.String2Date;

public class HibernateAppDAO {

    public static void main(String[] args) {
        final String2Date s2d = new String2Date() {
        };
        UserManager userManager = new UserManager();
        HotSpotManager hotSpotManager = new HotSpotManager();

        /**
         * Objects creation
         */
        // Users
        User user1 = new User("nel_12633", "nadimeltaha@gmail.com", "1234",
                "Badalona", "Spain", "Nadim", "El Taha",
                s2d.getDate("17/08/1989"));
        User user2 = new User("ubetweendays", "andujar.anna@gmail.com", "4321",
                "Castelldefels", "Spain", "Anna", "Andujar",
                s2d.getDate("15/07/1990"));
        User userdummy = new User("dummy", "dummy@upcontrol.com", "1111",
                "dummyCity", "dummyLand", "Dumminique", "yankins",
                s2d.getDate("1970/01/01"));

        // HotSpots
        HotSpot hotspot1 = new HotSpot("41.30855234, 2.0168245", "Radar",
                s2d.getDate("17/10/2013"), "Ford Focus C-Max matricula B0598LZ");
        HotSpot hotspot2 = new HotSpot("41.25575234, 1.8768245", "radar",
                s2d.getDate("17/10/2013"), "Odahviing Ref ID 45921");
        HotSpot hotdummy = new HotSpot("00,11", "dummydar",
                s2d.getDate("01/01/1970"), "dummy spoti");
        HotSpot hotspot3 = new HotSpot("41.27775234, 2.00768245", "Accidente",
                s2d.getDate("12/18/2013"), "Choque frontal");
        HotSpot hotspot4 = new HotSpot("42.25575234, 1.9768245", "Precaucion",
                s2d.getDate("12/18/2013"), "Sin descripci��n");
        /**
         * UserManager tests
         */
        // saveNewUser
        userManager.saveNewUser(user1);
        userManager.saveNewUser(user2);
        userManager.saveNewUser(userdummy);

        // findByUserName
        System.out.println("\nfindByUserName:");
        User user11 = userManager.findByUserName("nel_12633");
        User user22 = userManager.findByUserName("ubetweendays");
        System.out.println("\tUser11: " + user11.getFirstName() + " "
                + user11.getLastName() + " \n\tUser22: "
                + user22.getFirstName() + " " + user22.getLastName());

        // loadAllUsers
        System.out.println("\nloadAllUsers:");
        List<User> allUsers = userManager.loadAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            User user1122 = allUsers.get(i);
            System.out.println("\tallUsers " + (i + 1) + " : "
                    + user1122.getUserName());
        }

        // findUserById
        System.out.println("\nfindUserById:");
        User user = userManager.findUserById(2);
        System.out.println("\tUserById: " + user.getUserName());

        // deleteUser
        if (userdummy.getUserName() != null) {
            System.out.println("\ndeleteUser: " + userdummy.getUserName());
            userManager.deleteUser(userdummy);
        } else {
            System.err.println("ERROR: User not found");
        }

        // updateUser
        if (user1.getUserName() != null) {
            System.out.println("Update User " + user1.getUserName());
            user1.setCountry("Catalonia");
            userManager.updateUser(user1);
        } else {
            System.err.println("ERROR: User not found");
        }

        /**
         * HotSpotManager tests
         */
        // saveNewHotSpot
        hotSpotManager.saveNewHotSpot(hotspot1, user1);
        hotSpotManager.saveNewHotSpot(hotspot2, user2);
        hotSpotManager.saveNewHotSpot(hotspot3, user1);
        hotSpotManager.saveNewHotSpot(hotdummy, user1);
        hotSpotManager.saveNewHotSpot(hotspot4, user2);

        // loadAllHotSpots
        System.out.println("\nloadAllHotSpots:");
        List<HotSpot> allHotSpots = hotSpotManager.loadAllHotSpots();
        for (int i = 0; i < allHotSpots.size(); i++) {
            HotSpot hots = allHotSpots.get(i);
            System.out.println("\tallHotSpots " + (i + 1) + " : "
                    + hots.getDescription());
        }

        // findHotSpotById
        System.out.println("\nfindHotSpotById:");
        HotSpot hotspot = hotSpotManager.findHotSpotById(1);
        System.out.println("\thotSpotById 1: " + hotspot.getDescription());

        // deleteHotSpot
        System.out.println("\ndeleteHotSpot: " + hotdummy.getDescription());
        hotSpotManager.deleteHotSpot(hotdummy);

        // updateHotSpot
        hotspot1.setDescription("WOLOLOOOOO");
        hotSpotManager.updateHotSpot(hotspot1);

        /**
         * User to HotSpots relationships tests
         */

        // loadAllUserHotSpots
        System.out.println("\nloadAllUserHotSpots: ");
        List<HotSpot> allUserHotSpots = hotSpotManager
                .loadAllUserHotpots(userManager.findByUserName("ubetweendays"));
        for (int i = 0; i < allUserHotSpots.size(); i++) {
            HotSpot hots = allUserHotSpots.get(i);
            System.out.println("\tallUserHotSpots " + (i + 1) + " : "
                    + hots.getDescription());
        }
    }
}
