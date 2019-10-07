package nl.han.oose.dea.spotitube.domain;

public class UserDTO {

    private String user;
    private String password;

    private static final String DUMMY_USERNAME = "jochem";
    private static final String DUMMY_PASSWORD = "123";

    public UserDTO() {
        this.user = DUMMY_USERNAME;
        this.password = DUMMY_PASSWORD;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() { return password; }

}
