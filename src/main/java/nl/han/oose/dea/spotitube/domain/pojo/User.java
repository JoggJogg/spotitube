package nl.han.oose.dea.spotitube.domain.pojo;

import org.apache.commons.codec.digest.DigestUtils;

public class User {

    private String user;
    private String password;

    public User() {}

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() { return password; }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean passWordsMatch(User databaseUser) {
        return DigestUtils.sha256Hex(this.password).equals(databaseUser.password);
    }
}
