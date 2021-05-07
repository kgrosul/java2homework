package k.grosul.savefromhackers;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.stream.Stream;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String name = "";

    private String birth = "";

    private String passportNumber = "";

    private String passportGiven = "";

    private String passportRegistration = "";

    public User() {
    }

    public User(String name, String birth, String passportNumber, String passportGiven, String passportRegistration) {
        this.name = name;
        this.birth = birth;
        this.passportNumber = passportNumber;
        this.passportGiven = passportGiven;
        this.passportRegistration = passportRegistration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportGiven() {
        return passportGiven;
    }

    public void setPassportGiven(String passportGiven) {
        this.passportGiven = passportGiven;
    }

    public String getPassportRegistration() {
        return passportRegistration;
    }

    public void setPassportRegistration(String passportRegistration) {
        this.passportRegistration = passportRegistration;
    }

    public Stream<String> allFields() {
        return Stream.of(name,
                birth,
                passportGiven,
                passportNumber,
                passportRegistration);
    }
    public boolean isRoot() {
        return allFields().allMatch(str -> str.equals("root"));
    }

    public boolean isFull() {
        return allFields().noneMatch(str -> str.equals(""));
    }

}


