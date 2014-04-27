package hibernate.manager;

import java.util.List;

import model.HotSpot;
import model.User;

public interface IUserManager {
    public User findByUserName(String userName);

    public List<User> loadAllUsers();

    public void saveNewUser(User user);

    public User findUserById(Integer id);

    public void deleteUser(User user);

    public void updateUser(User user);

    public void saveUserHotSpot(User user, HotSpot hotspot);

}
