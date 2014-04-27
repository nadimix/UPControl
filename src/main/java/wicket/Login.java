package wicket;

import hibernate.manager.UserManager;
import model.User;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import session.SesionUser;

/**
 * Homepage
 */

public class Login extends WebPage {

	private static final long serialVersionUID = 1L;
	private String usernameid;
	private String passwordid;

	public Login() {

		Form form = new Form("formid", new CompoundPropertyModel(this)) {

			protected void onSubmit() {

				System.out.println("boton pulsado");
				System.out.println(usernameid);
				System.out.println(passwordid);

				if (usernameid.equals("")) {
					// poner un pannel de esos con este mensaje
					error("Username is mandatory!");
					// target.appendJavaScript("bootbox.alert('Username is mandatory!');");
				} else if (passwordid.equals("")) {
					// poner un pannel de esos con este mensaje
					// target.appendJavaScript("bootbox.alert('Password is mandatory!');");
					error("Password is mandatory!");
				} else {

					UserManager userManager = new UserManager();

					User user1 = null;
					user1 = userManager.findByUserName(usernameid);

					if (user1 == null) {
						System.out.println("Usuario no existe!");
						error("User does not exist!");
					} else if (user1.getPassword().equals(passwordid)) {
						System.out.println("Login correcto!");
						SesionUser s = (SesionUser) getSession();
						s.setUser(user1);
						setResponsePage(Index.class);
					} else {
						System.out.println("Password Incorrecto!");
						error("Wrong password!");
					}

				}

			}

		};

		
		form.add(new BookmarkablePageLink("register", Register.class));
		add(form);
		form.add(new FeedbackPanel("errorMsg"));
		form.add(new TextField("usernameid"));
		form.add(new PasswordTextField("passwordid"));

	}
}
