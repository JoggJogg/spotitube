package nl.han.oose.dea.spotitube.domain;

public class TokenDTO {

    private String token;
    private String user;

    private static final String DUMMY_TOKEN = "1234-1234-1234";

    public TokenDTO() {

    }

    public TokenDTO(UserDTO user) {
        this.token = generateToken();
        this.user = user.getUser();
    }

    private String generateToken() {
        return DUMMY_TOKEN;
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }

}
