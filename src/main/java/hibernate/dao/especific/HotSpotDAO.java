package hibernate.dao.especific;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import util.HibernateUtil;
import hibernate.dao.generic.GenericHotSpotDAO;
import model.HotSpot;
import model.User;

public class HotSpotDAO extends GenericHotSpotDAO<HotSpot, Integer> implements
        IHotSpotDAO {

    private static final long serialVersionUID = 1L;

    public List<HotSpot> findAllHotSpot(User user) {
        List<HotSpot> userHotSpot = new ArrayList<HotSpot>();
        Integer userID = user.getId();
        // SQL: select user_hotspot.HOTSPOT_ID from user_hotspot where
        // user_hotspot.USER_ID = 1;
        // HQL?: select a.firstName, a.lastName from Book b join b.authors a
        // where b.id = :id
        String hql = "select h.id from User a join a.hot h where a.id = :userID";
        Query query = HibernateUtil.getSession().createQuery(hql)
                .setParameter("userID", userID);
        userHotSpot = findMany(query);
        return userHotSpot;
    }
    
    public HotSpot findByHotSpotByDescription(String description) {
        HotSpot hot = null;
        String hql = "from HotSpot hot where hot.description = :description";
        Query query = HibernateUtil.getSession().createQuery(hql)
                .setParameter("description", description);
        hot = findOne(query);
        return hot;
    }
}
