package rest;

import hibernate.manager.HotSpotManager;
import hibernate.manager.UserManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import to.HotSpotTO;
import to.UserTO;
import util.String2Date;
import model.HotSpot;
import model.User;

import com.google.gson.Gson;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
import com.google.gson.JsonSyntaxException;

/* He llamado al servlet de jersey jerseyRest
 * Cualquier cambio afecta a este nombre en el web.xml y en el api.js*/

@Path("/hotspot")
public class RestHotspots {

    @Context
    HttpServletRequest request;

    @Context
    private SecurityContext security;

    final String2Date s2d = new String2Date() {
    };

    HotSpotManager mgmt;
    UserManager mgmt2;
    Gson gson;

    public RestHotspots() {
        super();
        mgmt = new HotSpotManager();
        mgmt2 = new UserManager();
        gson = new Gson();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listHotspots")
    public Response getHotspots() {
        List<HotSpot> ls = mgmt.loadAllHotSpots();

        List<HotSpot> newlist = new ArrayList<HotSpot>();
        for (int i = 0; i < ls.size(); i++) {
            HotSpot h = new HotSpot(ls.get(i).getCoordinates(), ls.get(i)
                    .getKind(), ls.get(i).getDate(), ls.get(i).getDescription());
            h.setId(ls.get(i).getId());
            newlist.add(h);
        }
        return Response.status(200).entity(gson.toJson(newlist)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listUserHotspots/{username}")
    public Response getUserHotspots(@PathParam("username") String username) {
        User user = (User) mgmt2.findByUserName(username);
        List<HotSpot> ls = mgmt.loadAllUserHotpots(user);

        List<HotSpot> newlist = new ArrayList<HotSpot>();
        for (int i = 0; i < ls.size(); i++) {
            HotSpot h = new HotSpot(ls.get(i).getCoordinates(), ls.get(i)
                    .getKind(), ls.get(i).getDate(), ls.get(i).getDescription());
            h.setId(ls.get(i).getId());
            newlist.add(h);
        }
        return Response.status(200).entity(gson.toJson(newlist)).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addHotspot")
    public Response addHotspot(String s) {

        HotSpotTO hp = gson.fromJson(s, HotSpotTO.class);

        User user = mgmt2.findByUserName(hp.getUser());

        HotSpot hotspot = new HotSpot(hp.getCoordinates(), hp.getKind(),
                s2d.getDate(hp.getDate()), hp.getDescription());
        Boolean b = mgmt.saveNewHotSpot(hotspot, user);

        if (b) {
            String result = "Hotspot saved " + hp.getDescription();
            return Response.status(201).entity(result).build();
        } else
            return Response.status(501).entity("Error").build();

    }
}
