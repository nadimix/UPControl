package hibernate.manager;

import hibernate.dao.especific.UserDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.HotSpot;
import model.User;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;

import util.HibernateUtil;

public class UserManager implements IUserManager {

    private UserDAO userDAO = new UserDAO();

    public User findByUserName(String userName) {
        User user = null;

        try {
            HibernateUtil.beginTransaction();
            user = userDAO.findByUserName(userName);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.err.println("Query returned more than one result");
            ex.printStackTrace();
            return null;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }

        return user;
    }

    public List<User> loadAllUsers() {
        List<User> allUsers = new ArrayList<User>();

        try {
            HibernateUtil.beginTransaction();
            allUsers = userDAO.findAll(User.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            // TODO handle this error
        }
        return allUsers;
    }

    public void saveNewUser(User user) {
        try {
            HibernateUtil.beginTransaction();
            userDAO.save(user);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.err.println("ERROR: Saving User: " + user.getUserName());
            HibernateUtil.rollbackTransaction();
        }
    }

    public User findUserById(Integer id) {
        User user = null;
        try {
            HibernateUtil.beginTransaction();
            user = (User) userDAO.findByID(User.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
        return user;
    }

    public void deleteUser(User user) {
        try {
            HibernateUtil.beginTransaction();
            Set<HotSpot> hot;
            hot = user.getHot();
            if (hot != null) {
                hot.removeAll(hot);
                user.setHot(hot);
            }
            userDAO.delete(user);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.err.println("ERROR: Deleting User: " + user.getUserName());
            HibernateUtil.rollbackTransaction();
        }
    }

    public void updateUser(User user) {
        try {
            HibernateUtil.beginTransaction();
            userDAO.update(user);
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            System.err.println("ERROR: Updating User: " + user.getUserName());
            HibernateUtil.rollbackTransaction();
        }
    }

    public void saveUserHotSpot(User user, HotSpot hotspot) {
        try {
            HibernateUtil.beginTransaction();
            Set<HotSpot> hot;
            hot = user.getHot();
            if (hot == null) {
                hot = new HashSet<HotSpot>(0);
            }
            hot.add(hotspot);
            user.setHot(hot);
            userDAO.update(user);
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            System.err.println("ERROR: Adding HotSpotToUser: "
                    + hotspot.getId() + " to User: " + user.getUserName());
            HibernateUtil.rollbackTransaction();
        }
    }
}
