package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({ @NamedQuery(name = "findUserByID",
        query = "from User usr where usr.id = :id") })
@Entity
@Table(name = "user", catalog = "upcontroldb", uniqueConstraints = {
        @UniqueConstraint(columnNames = "USER_NAME"),
        @UniqueConstraint(columnNames = "EMAIL") })
public class User implements Serializable {

    private Integer id;
    private String userName;
    private String email;
    private String password;
    private String city;
    private String country;
    private String firstName; // nullable
    private String lastName; // nullable
    private Date birth; // nullable
    private Set<HotSpot> hot = new HashSet<HotSpot>(0);

    public User() {
    }

    public User(String userName, String email, String password, String city,
            String country, String firstName, String lastName, Date birth) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.city = city;
        this.country = country;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
    }

    public User(String userName, String email, String password, String city,
            String country, String firstName, String lastName, Date birth,
            Set<HotSpot> hot) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.city = city;
        this.country = country;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.hot = hot;
    }
    
    

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_ID", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "USER_NAME", unique = true, nullable = false)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "EMAIL", unique = true, nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH", nullable = false)
    public Date getBirth() {
        return this.birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Column(name = "CITY", nullable = false)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "COUNTRY", nullable = false)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    public Set<HotSpot> getHot() {
        return this.hot;
    }

    public void setHot(Set<HotSpot> hot) {
        this.hot = hot;
    }

    private static final long serialVersionUID = 1L;

}
