package hibernate.manager;

import hibernate.dao.especific.HotSpotDAO;
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

public class HotSpotManager implements IHotSpotManager {

    private HotSpotDAO hotspotDAO = new HotSpotDAO();
    private UserDAO userDAO = new UserDAO();

    public List<HotSpot> loadAllHotSpots() {
        List<HotSpot> allHotspots = new ArrayList<HotSpot>();
        try {
            HibernateUtil.beginTransaction();
            allHotspots = hotspotDAO.findAll(HotSpot.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.err.println("Error while loading all hotspots.");
            //return null;
        }
        return allHotspots;
    }

    public void deleteHotSpot(HotSpot hotspot) {
        try {
            HibernateUtil.beginTransaction();
            hotspotDAO.delete(hotspot);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.err.println("Error while deleting this hotspot " + hotspot
                    + ".");
            HibernateUtil.rollbackTransaction();
        }
    }

    public List<HotSpot> loadAllUserHotpots(User user) {
        List<HotSpot> allUserHotSpot = null;
        try {
            HibernateUtil.beginTransaction();
            allUserHotSpot = new ArrayList<HotSpot>(user.getHot());
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            e.printStackTrace();
            //return null;
        }
        return allUserHotSpot;
    }

    public Boolean saveNewHotSpot(HotSpot hotspot, User user) {
        try {
            HibernateUtil.beginTransaction();
            Set<HotSpot> hot;
            hot = user.getHot();
            if (hot == null) {
                hot = new HashSet<HotSpot>(0);
            }
            hot.add(hotspot);
            user.setHot(hot);

            hotspot.setUser(user);
            hotspotDAO.save(hotspot);
            userDAO.save(user);
            HibernateUtil.commitTransaction();
            return true;
        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
            System.err.println("Error while saving hotspot: "
                    + hotspot.getDescription());
            return false;
        }
    }

    public HotSpot findHotSpotById(Integer id) {
        HotSpot hot = null;
        try {
            HibernateUtil.beginTransaction();
            hot = (HotSpot) hotspotDAO.findByID(HotSpot.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            //return null;
        }
        return hot;
    }

    public void updateHotSpot(HotSpot hotspot) {
        try {
            HibernateUtil.beginTransaction();
            hotspotDAO.update(hotspot);
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            System.err.println("ERROR: Updating Hotspot: "
                    + hotspot.getCoordinates());
            HibernateUtil.rollbackTransaction();
        }
    }

    public void saveHotSpotUser(User user, HotSpot hotspot) {
        try {
            HibernateUtil.beginTransaction();
            hotspot.setUser(user);
            hotspotDAO.update(hotspot);
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            System.err.println("ERROR: Adding HotSpot: " + hotspot.getId()
                    + " to User: " + user.getUserName());
            HibernateUtil.rollbackTransaction();
        }
    }

    public HotSpot findByDescription(String description) {
        HotSpot hot = null;

        try {
            HibernateUtil.beginTransaction();
            hot = hotspotDAO.findByHotSpotByDescription(description);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.err.println("Query returned more than one result");
            ex.printStackTrace();
            //return null;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            //return null;
        }

        return hot;
    }
}
