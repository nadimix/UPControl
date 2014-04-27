package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({ @NamedQuery(name = "findHotSpotByID",
        query = "from HotSpot hot where hot.id = :id") })
@Entity
@Table(name = "hotspot", catalog = "upcontroldb")
public class HotSpot implements Serializable {

    private Integer id;
    private String kind;
    private String coordinates;
    private Date date;
    private String description;
    private User user;

    public HotSpot() {
    }

    public HotSpot(String coordinates, String kind, Date date,
            String description) {
        this.coordinates = coordinates;
        this.setDate(date);
        this.kind = kind;
        this.description = description;
    }

    public HotSpot(String coordinates, String kind, Date date,
            String description, User user) {
        this.coordinates = coordinates;
        this.setDate(date);
        this.kind = kind;
        this.description = description;
        this.setUser(user);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "HOTSPOT_ID", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "KIND", nullable = false)
    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Column(name = "COORDINATES", nullable = false)
    public String getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE", nullable = false)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "DESCRIPTION", nullable = true)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private static final long serialVersionUID = 1L;

}
