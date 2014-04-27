package to;

import java.io.Serializable;
import java.util.Date;

import model.User;

@SuppressWarnings("serial")
public class UserTO implements Serializable {

    private Integer id;
    private String userName;
    private String email;
    private String password;
    private String city;
    private String country;
    private String firstName; // nullable
    private String lastName; // nullable
    private Date birth; // nullable
    
    public UserTO() {
        super();
    }

    public UserTO(Integer id, String userName, String email, String password,
            String city, String country, String firstName, String lastName,
            Date birth) {
        super();
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.city = city;
        this.country = country;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
    }

    public UserTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birth = user.getBirth();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
    
}
