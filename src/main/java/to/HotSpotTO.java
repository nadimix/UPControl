package to;

import java.io.Serializable;
import java.util.Date;

import model.HotSpot;
import model.User;

public class HotSpotTO implements Serializable {
    private Integer id;
    private String kind;
    private String coordinates;
    private String date;
    private String description;
    private String username;

    public HotSpotTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public HotSpotTO(Integer id, String kind, String coordinates, String date,
            String description, String username) {
        super();
        this.id = id;
        this.kind = kind;
        this.coordinates = coordinates;
        this.date = date;
        this.description = description;
    }

    public HotSpotTO(HotSpot hotSpot) {
        this.id = hotSpot.getId();
        this.kind = hotSpot.getKind();
        this.coordinates = hotSpot.getCoordinates();
        this.date = hotSpot.getDate().toString();
        this.description = hotSpot.getDescription();
        this.username = hotSpot.getUser().getUserName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

}
