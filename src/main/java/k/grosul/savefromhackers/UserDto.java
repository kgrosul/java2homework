package k.grosul.savefromhackers;

import javax.persistence.ManyToOne;

@SuppressWarnings("unused")
public class UserDto {
    private String name;
    private String birth;
    private String passportNumber;
    private String passportGiven;
    private String passportRegistration;

    public UserDto(){}

    UserDto(String name, String birth, String passportNumber, String passportGiven, String passportRegistration) {
        this.name = name;
        this.birth = birth;
        this.passportGiven = passportGiven;
        this.passportNumber = passportNumber;
        this.passportRegistration = passportRegistration;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}