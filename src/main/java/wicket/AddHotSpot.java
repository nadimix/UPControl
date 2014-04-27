package wicket;

import hibernate.manager.HotSpotManager;
import hibernate.manager.UserManager;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import model.HotSpot;
import model.User;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import session.SesionUser;
import util.String2Date;

public class AddHotSpot extends AuthenticatedPage {

    private static final long serialVersionUID = 1L;
    private String description;
    private String coordinates;

    private static final List<String> KIND = Arrays.asList(new String[] {
            "Radar", "Mobile radar", "Caution", "Accident" });

    private String selectedKind = "Radar";

    @SuppressWarnings({ "unchecked", "serial", "rawtypes" })
    public AddHotSpot(final PageParameters parameters) {

        final SesionUser s = (SesionUser) getSession();
        if (s.getUser() == null) {
            getSession().invalidateNow();
            getRequestCycle().setResponsePage(Login.class);
            setResponsePage(Login.class);
        }

        DropDownChoice<String> listKind = new DropDownChoice<String>("kind",
                new PropertyModel<String>(this, "selectedKind"), KIND);

        final TextArea<String> description = new TextArea<String>(
                "description", Model.of(""));
        description.setRequired(true);
        description.setLabel(Model.of("Description"));

        final TextField<String> coordinates = new TextField<String>(
                "coordinates", Model.of(""));
        coordinates.setRequired(true);
        coordinates.setLabel(Model.of("Coordinates"));

        Form form = new Form("formid", new CompoundPropertyModel(this)) {

            protected void onSubmit() {

                HotSpotManager hotspotManager = new HotSpotManager();
                UserManager usermanager = new UserManager();

                String2Date s2d = new String2Date() {
                };

                Date date = s2d.getDate(s2d.getCurrentDate());

                PageParameters pageParameters = new PageParameters();
                pageParameters.add("description", description);
                pageParameters.add("coordinates", coordinates);

                String[] lista = coordinates.getInput().split(",");

                String sSubCadena_1 = lista[1].substring(1, 15);
                System.out.println(sSubCadena_1);

                String sSubCadena_2 = lista[0].substring(1, 15);
                System.out.println(sSubCadena_2);

                String coordenadas_buenas = sSubCadena_2 + "," + sSubCadena_1;

                HotSpot hotspot = new HotSpot(coordenadas_buenas, selectedKind,
                        date, description.getInput());

                System.out.println("description " + description + " coord "
                        + coordinates);
                if (hotspotManager.saveNewHotSpot(hotspot, usermanager
                        .findByUserName(s.getUser()
                                .getUserName())) == true) {
                    User user = usermanager.findByUserName(s.getUser()
                            .getUserName());
                    s.setUser(user);
                    coordinates.setModelValue(null);
                    description.setModelValue(null);
                } else {
                    error("Operation failed. Try again.");
                }
                setResponsePage(AddHotSpot.class);
            }

        };
        add(form);
        form.add(listKind);
        form.add(description);
        form.add(coordinates);
        form.add(new FeedbackPanel("errorMsg"));

        Link logoutLink = new Link("logout") {
            public void onClick() {
                getSession().invalidateNow();
                setResponsePage(Login.class);
            }
        };
        add(logoutLink);

        add(new BookmarkablePageLink("CreateRoute", CreateRoute.class));
        add(new BookmarkablePageLink("UserProfile", MyProfile.class));
        add(new BookmarkablePageLink("AddHotSpot", AddHotSpot.class));
        add(new BookmarkablePageLink("Index", Index.class));
        //add(new Label("user", s.getUser().getUserName()));
    }
}
