package hibernate.dao.especific;

import hibernate.dao.generic.GenericUserDAO;
import model.User;

import org.hibernate.Query;

import util.HibernateUtil;

public class UserDAO extends GenericUserDAO<User, Integer> implements IUserDAO {

    public User findByUserName(String userName) {
        User user = null;
        String hql = "from User usr where usr.userName = :userName";
        Query query = HibernateUtil.getSession().createQuery(hql)
                .setParameter("userName", userName);
        user = findOne(query);
        return user;
    }

    private static final long serialVersionUID = 1L;

}