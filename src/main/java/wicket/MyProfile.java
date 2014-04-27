package wicket;

import hibernate.manager.HotSpotManager;
import hibernate.manager.UserManager;

import java.util.ArrayList;
import java.util.List;

import model.HotSpot;
import model.User;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import session.SesionUser;
import util.String2Date;

public class MyProfile extends AuthenticatedPage {

    private static final long serialVersionUID = 1L;

    private static List<String> HOTSPOTS = new ArrayList<String>();

    // Holds checkbox values
    private ArrayList<String> selectedHotSpots = new ArrayList<String>();

    @SuppressWarnings({ "unchecked", "serial", "rawtypes" })
    public MyProfile(final PageParameters parameters) {
        
        final SesionUser s = (SesionUser) getSession();
        if (s.getUser() == null) {
            getSession().invalidateNow();
            getRequestCycle().setResponsePage(Login.class);
            setResponsePage(Login.class);
        }

        add(new BookmarkablePageLink("UserProfile", MyProfile.class));
        add(new BookmarkablePageLink("CreateRoute", CreateRoute.class));
        add(new BookmarkablePageLink("AddHotSpot", AddHotSpot.class));
        add(new BookmarkablePageLink("Index", Index.class));
        UserManager usermanager = new UserManager();

        final User user = usermanager.findByUserName(s.getUser().getUserName());
        ArrayList<HotSpot> hp = new ArrayList<HotSpot>(user.getHot());

        HOTSPOTS.clear();

        for (int i = 0; i < hp.size(); i++) {
            HOTSPOTS.add(hp.get(i).getDescription());
        }

        String2Date s2d = new String2Date() {
        };

        String date = s2d.getDate(user.getBirth());

        String city_country = user.getCity() + ", " + user.getCountry();

        final CheckBoxMultipleChoice<String> listHotSpots = new CheckBoxMultipleChoice<String>(
                "hotspots", new Model(selectedHotSpots), HOTSPOTS);

        Form<?> form = new Form<Void>("hotspotForm") {
            @Override
            protected void onSubmit() {
                HotSpotManager hotspotManager = new HotSpotManager();
                for (int j = 0; j < selectedHotSpots.size(); j++) {
                    System.out.println(selectedHotSpots.get(j));
                    hotspotManager.deleteHotSpot(hotspotManager
                            .findByDescription(selectedHotSpots.get(j)));
                }
                user.setHot(null);
                s.setUser(user);

                setResponsePage(MyProfile.class);
            }
        };
        add(form);
        form.add(listHotSpots);
        
        Link logoutLink = new Link("logout") {
            public void onClick() {
                getSession().invalidate();
                setResponsePage(Login.class);
            }
        };
        add(logoutLink);
        
        add(new Label("country", city_country));
        add(new Label("email", user.getEmail()));
        add(new Label("firstname", user.getFirstName()));
        add(new Label("lastname", user.getLastName()));
        add(new Label("username", user.getUserName()));
        add(new Label("date", date));
    }
}
