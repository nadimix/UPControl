package session;



import model.User;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;


public class SesionUser extends WebSession{
	

	private User user = null;
	
    public SesionUser(Request request) {
        super (request);
    }
    
    public void setUser (User u)
    {
    	user =u;
    }
    public User getUser () {
        return user;
    }
}
