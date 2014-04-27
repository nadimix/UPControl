package testManager;

import static org.junit.Assert.assertEquals;

import java.util.List;

import hibernate.manager.UserManager;
import model.User;

import org.junit.Test;

import util.String2Date;

public class TUser {

    UserManager mgmt = new UserManager();
    final String2Date s2d = new String2Date() {
    };

    // @Before
    public TUser() {
        // mgmt = new UserManager();
    }

    @Test
    public void testSaveNewUser() {
        int numbUsers = mgmt.loadAllUsers().size();
        User u = new User("aaaaa", "dummy@upcontrol.com", "1111", "dummyCity",
                "dummyLand", "Dumminique", "yankins", s2d.getDate("1970/01/01"));
        mgmt.saveNewUser(u);
        assertEquals(numbUsers + 1, mgmt.loadAllUsers().size());
    }

    @Test
    public void testFindByUsername() {
        User u = mgmt.findByUserName("ubetweendays");
        assertEquals("ubetweendays", u.getUserName());
    }

    @Test
    public void testLoadAllUsers() {
        int size = mgmt.loadAllUsers().size();
        List<User> user = mgmt.loadAllUsers();
        assertEquals(size, user.size());
    }

    @Test
    public void testFindUserById() {
        User u = mgmt.findUserById(2);
        assertEquals("ubetweendays", u.getUserName());
    }

    @Test
    public void testUpdateUser() {
        User u = mgmt.findByUserName("ubetweendays");
        u.setCountry("BCN");
        mgmt.updateUser(u);
        assertEquals("BCN", u.getCountry());
    }

    @Test
    public void deleteUser() {
        int numbUsers = mgmt.loadAllUsers().size();
        mgmt.deleteUser(mgmt.findByUserName("aaaaa"));
        assertEquals(numbUsers - 1, mgmt.loadAllUsers().size());

    }

}
