package nl.han.oose.dea.spotitube.domain;

import java.util.UUID;

public class Token extends DomainObject {

    private String token;
    private String user;

    private static final String UUID_SET = "38400000-8cf0-11bd-b23e-10b96e4ef00d";

    public Token() {}

    public Token(User user) {
        this.token = generateToken();
        this.user = user.getUser();
    }

    private String generateToken() {
        return UUID.fromString(UUID_SET).randomUUID().toString();
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }

}
