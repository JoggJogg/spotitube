package nl.han.oose.dea.spotitube.domain;

import java.util.UUID;

public class Token extends DomainObject {

    private String token;
    private String user;

    public Token() {}

    public Token(User user) {
        this.token = generateToken();
        this.user = user.getUser();
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }

}
