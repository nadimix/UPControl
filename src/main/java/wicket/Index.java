package wicket;

//import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import session.SesionUser;

public class Index extends AuthenticatedPage {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings({ "unchecked", "serial", "rawtypes" })
    public Index(final PageParameters parameters) {
        
        SesionUser s = (SesionUser) getSession();
        if(s.getUser() == null) {
            getSession().invalidateNow();
            setResponsePage(Login.class);
        }
        
        add(new BookmarkablePageLink("CreateRoute", CreateRoute.class));
        add(new BookmarkablePageLink("UserProfile", MyProfile.class));
        add(new BookmarkablePageLink("AddHotSpot", AddHotSpot.class));
        add(new BookmarkablePageLink("Index", Index.class));

        Link logoutLink = new Link("logout") {
            public void onClick() {
                getSession().invalidateNow();
                setResponsePage(Login.class);
            }
        };
        add(logoutLink);
    }
}
