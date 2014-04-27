package wicket;

import hibernate.manager.UserManager;

import java.util.Date;

import model.User;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import session.SesionUser;
import util.String2Date;

public class Register extends WebPage {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings({ "unchecked", "rawtypes", "serial" })
    public Register(final PageParameters parameters) {


        final TextField<String> username = new TextField<String>("username",
                Model.of(""));
        username.setRequired(true);
        username.add(new TextFieldValidator());

        final TextField<String> firstname = new TextField<String>("firstname",
                Model.of(""));
        firstname.setRequired(true);
        firstname.add(new TextFieldValidator());

        final TextField<String> lastname = new TextField<String>("lastname",
                Model.of(""));
        lastname.setRequired(true);
        lastname.add(new TextFieldValidator());

        final TextField<String> email = new TextField<String>("email",
                Model.of(""));
        email.setRequired(true);
        email.add(new TextFieldValidator());

        final PasswordTextField password1 = new PasswordTextField("password1",
                Model.of(""));
        password1.setLabel(Model.of("Password"));

        final PasswordTextField password2 = new PasswordTextField("password2",
                Model.of(""));
        password2.setLabel(Model.of("Confirm Password"));

        final TextField<String> date = new TextField<String>("date",
                Model.of(""));
        date.setRequired(true);
        date.add(new TextFieldValidator());

        final TextField<String> country = new TextField<String>("country",
                Model.of(""));
        country.setRequired(true);
        country.add(new TextFieldValidator());

        final TextField<String> city = new TextField<String>("city",
                Model.of(""));
        city.setRequired(true);
        city.add(new TextFieldValidator());

        Form form = new Form("formid", new CompoundPropertyModel(this)) {

            @Override
            protected void onSubmit() {

                UserManager userManager = new UserManager();

                PageParameters pageParam = new PageParameters();
                pageParam.add("username", username.getModelObject());
                pageParam.add("firstname", firstname.getModelObject());
                pageParam.add("lastname", lastname.getModelObject());
                pageParam.add("email", email.getModelObject());
                pageParam.add("password1", password1.getModelObject());
                pageParam.add("date", date.getModelObject());
                pageParam.add("country", country.getModelObject());
                pageParam.add("city", city.getModelObject());

                User user1 = userManager.findByUserName(username.getInput());
                if (user1 == null) {
                    String2Date s2d = new String2Date() {
                    };
                    Date d = s2d.getDate(date.getInput());
                    User user = new User(username.getInput(), email.getInput(),
                            password1.getInput(), city.getInput(),
                            country.getInput(), firstname.getInput(),
                            lastname.getInput(), d);
                    userManager.saveNewUser(user);
                    System.out.println("Registro correcto");
                    SesionUser s = (SesionUser) getSession();
                    s.setUser(user);
                    setResponsePage(Index.class, pageParam);

                } else {
                    System.out.println("El usuario que quieres a–adir "
                            + "ya existe");
                    error("UPControl has one user with the same name, try it again!");
                }

            }

        };
        add(form);
        form.add(username);
        form.add(firstname);
        form.add(lastname);
        form.add(email);
        form.add(password1);
        form.add(password2);
        form.add(date);
        form.add(country);
        form.add(city);
        form.add(new FeedbackPanel("errorMsg"));
        form.add(new EqualPasswordInputValidator(password1, password2));
    }

}
