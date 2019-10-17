package nl.han.oose.dea.spotitube.domain;

public class User extends DomainObject{

    private String user;
    private String password;

    private static final String DUMMY_USERNAME = "jochem";
    private static final String DUMMY_PASSWORD = "123";

    public User() {

    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() { return password; }

    public boolean passWordsMatch(User user) {
        return password.equals(user.getPassword());
    }


}
