package wicket;

import org.apache.wicket.Session;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.IRequestCycleSettings.RenderStrategy;

import session.AuthenticatedPage;
import session.SesionUser;
//import org.hibernate.Session;

public class WicketApplication extends WebApplication {

    // public WicketApplication() {
    //
    //
    // }
    @Override
    public void init() {
        super.init();

        // Session session_h = null;
        // session_h = HibernateUtil.getSessionFactory().openSession();
        // Indico que en caso de que expire la sesi�n me env�en a la p�gina de
        // login
        getApplicationSettings().setPageExpiredErrorPage(Login.class);

        /*
         * Ahora indico que al acceder a cualquier p�gina que herede de
         * AuthenticatedPage.class se compruebe primero si en la sesi�n se ha
         * almacenado alg�n usuario. En caso de que no sea asi es que se est�
         * intentando acceder a una p�gina sin pasar por el login, porque es en
         * el log�n en el �nico sitio en el que se guarda el usuario en la
         * sesi�n
         */
        getSecuritySettings().setAuthorizationStrategy(
                new SimplePageAuthorizationStrategy(AuthenticatedPage.class,
                        Login.class) {
                    protected boolean isAuthorized() {
                        return ((SesionUser) Session.get()).getUser() != null;
                    }
                });
        //getRequestCycleSettings().setRenderStrategy(RenderStrategy.ONE_PASS_RENDER);
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new SesionUser(request);
    }

    public Class getHomePage() {
        return Login.class;
    }

}
