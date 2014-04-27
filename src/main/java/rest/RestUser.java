package rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hibernate.manager.UserManager;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import to.UserTO;
import model.User;

import com.google.gson.Gson;

@Path("/user")
public class RestUser {

    @Context
    HttpServletRequest request;
    @Context
    private SecurityContext security;
    UserManager mgmt;
    Gson gson;

    public RestUser() {
        super();
        mgmt = new UserManager();
        gson = new Gson();
        System.out.println("Running services.........");
    }

    @GET
    @Path("/getUserById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Integer id) {

        User user = (User) mgmt.findUserById(id);
        UserTO userTO = new UserTO(user);

        return Response.status(200).entity(gson.toJson(userTO)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getUserByUsername/{username}")
    public Response getUserByUsername(@PathParam("username") String username) {

        User user = (User) mgmt.findByUserName(username);
        UserTO u = new UserTO(user);

        return Response.status(200).entity(gson.toJson(u)).build();
    }

    @GET
    @Path("/listUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadAllUsers() {
        List<User> list = mgmt.loadAllUsers();

        List<User> newlist = new ArrayList<User>();
        for (int i = 0; i < list.size(); i++) {

            User u = new User(list.get(i).getUserName(),
                    list.get(i).getEmail(), list.get(i).getPassword(), list
                            .get(i).getCity(), list.get(i).getCountry(), list
                            .get(i).getFirstName(), list.get(i).getLastName(),
                    list.get(i).getBirth());
            u.setId(list.get(i).getId());
            newlist.add(u);
        }

        return Response.status(200).entity(gson.toJson(newlist)).build();
    }
}
