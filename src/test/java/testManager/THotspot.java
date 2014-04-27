package testManager;

import static org.junit.Assert.assertEquals;
import hibernate.manager.HotSpotManager;
import hibernate.manager.UserManager;

import java.util.List;

import model.HotSpot;
import model.User;

import org.junit.Test;

import util.String2Date;

public class THotspot {

    HotSpotManager mgmt;
    UserManager mgmt2;
    final String2Date s2d = new String2Date() {
    };

    public THotspot() {
        super();
        System.out.println("Running test...");
        mgmt = new HotSpotManager();
        mgmt2 = new UserManager();
    }

    @Test
    public void testSaveNewHotspot() {

        Integer size = mgmt.loadAllHotSpots().size();
        User u = new User("fdsfssf", "dummy@upcontrol.com", "1111",
                "dummyCity", "dummyLand", "Dumminique", "yankins",
                s2d.getDate("1970/01/01"));
        mgmt2.saveNewUser(u);
        HotSpot hp = new HotSpot("53.2557, 4.9768245", "Precaucion",
                s2d.getDate("12/18/2013"), "---");
        mgmt.saveNewHotSpot(hp, u);
        assertEquals(size + 1, mgmt.loadAllHotSpots().size());
    }

    @Test
    public void testAllHotspots() {
        int size = mgmt.loadAllHotSpots().size();
        List<HotSpot> hp = mgmt.loadAllHotSpots();
        assertEquals(size, hp.size());
    }

    @Test
    public void testFindHotspotById() {
        HotSpot hp = mgmt.findHotSpotById(2);
        assertEquals("41.25575234, 1.8768245", hp.getCoordinates());
    }

    @Test
    public void deleteHotspot() {
        int numbHPs = mgmt.loadAllHotSpots().size();
        mgmt.deleteHotSpot(mgmt.findHotSpotById(6));
        assertEquals(numbHPs - 1, mgmt.loadAllHotSpots().size());
    }
    
    @Test
    public void deleteHotspotbyDescription() {
        int numbHPs = mgmt.loadAllHotSpots().size();
        mgmt.deleteHotSpot(mgmt.findByDescription("---"));
        assertEquals(numbHPs - 1, mgmt.loadAllHotSpots().size());
    }

    @Test
    public void testUpdateHotspot() {
        HotSpot hp = mgmt.findHotSpotById(1);
        hp.setDescription("...");
        mgmt.updateHotSpot(hp);
        assertEquals("...", hp.getDescription());
    }

}
