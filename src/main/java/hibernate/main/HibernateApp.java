package hibernate.main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.HotSpot;
import model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import util.String2Date;

@Deprecated
public class HibernateApp {

    public static void main(String[] args) {

        // Implements rollback, this is important for stability
        Session session = null;
        Transaction tx = null;

        System.out.println("HibernateApp init");

        try {
            final String2Date s2d = new String2Date() {
            };

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tx.setTimeout(5);

            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            // Insert data
            User user = new User("nel_12633", "nadimeltaha@gmail.com", "1234",
                    "Badalona", "Spain", "Nadim", "El Taha",
                    s2d.getDate("08/17/1989"));
            User user2 = new User("ubetweendays", "andujar.anna@gmail.com",
                    "4321", "Castelldefels", "Spain", "Anna", "AndÃºjar",
                    s2d.getDate("07/15/1990"));

            HotSpot hotspot1 = new HotSpot("41.30855234, 2.0168245", "Radar fijo",
                    s2d.getDate("10/17/2013"),
                    "Ford Focus C-Max matricula B0598LZ");
            HotSpot hotspot2 = new HotSpot("41.25575234, 1.8768245", "Radar movil",
                    s2d.getDate("10/17/2013"), "Odahviing Ref ID 45921");
            
            HotSpot hotspot3 = new HotSpot("41.27775234, 2.00768245", "Accidente",
                    s2d.getDate("12/18/2013"),"Choque frontal");
            HotSpot hotspot4 = new HotSpot("42.25575234, 1.9768245", "Precaucion",
                    s2d.getDate("12/18/2013"), "Sin descripción");

            Set<HotSpot> hot = new HashSet<HotSpot>();
            hot.add(hotspot1);
            hot.add(hotspot2);
            hot.add(hotspot3);
            hot.add(hotspot4);

            Set<HotSpot> hot2 = new HashSet<HotSpot>();
            hot2.add(hotspot1);
            hot2.add(hotspot3);

            user.setHot(hot);
            user2.setHot(hot2);

            session.save(user);
            session.save(user2);

            session.getTransaction().commit();

            // Obtain user data
            Query query = session.getNamedQuery("findUserByID");
            query.setParameter("id", 1);
            List<?> list = query.list();

            for (int i = 0; i < list.size(); i++) {
                User usr = (User) list.get(i);
                System.out.println("Username: " + usr.getUserName()
                        + " email: " + usr.getEmail() + " Name: "
                        + usr.getFirstName() + " " + usr.getLastName()
                        + " City: " + usr.getCity() + " Country: "
                        + usr.getCountry());
            }

            // Obtain hotspot data
            Query query2 = session.getNamedQuery("findHotSpotByID");
            query2.setParameter("id", 1);
            List<?> list2 = query2.list();

            for (int i = 0; i < list2.size(); i++) {
                HotSpot hotsp = (HotSpot) list2.get(i);
                System.out.println("kind: " + hotsp.getKind()
                        + " coordinates: " + hotsp.getCoordinates() + " date: "
                        + hotsp.getDate() + " descripcion: "
                        + hotsp.getDescription());
            }
            // Simple HQL query without using named queries
            Query query3 = session.createQuery("from User where id = 2 ");
            List<?> list3 = query3.list();
            User user3 = (User) list3.get(0);

            System.out.println("User3: " + user3.getUserName());

            tx.commit();

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                System.out.println("Couldn't roll back transaction" + rbe);
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                System.out.println("Finished");
            }
        }

    }
}
