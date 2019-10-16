package nl.han.oose.dea.spotitube.presentation;

import nl.han.oose.dea.spotitube.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class LoginServiceTest {

    private LoginService sut;
    private User mockedUser;

    private static final int HTTP_CREATED = 201;

    @BeforeEach
    public void setup() {
        sut = new LoginService();
    }

    @Test
    public void handleLoginSuccessReturns201StatusCode() {
        // Arrange
        mockedUser = Mockito.mock(User.class);
        // Act
        Response response = sut.handleLogin(mockedUser);
        // Assert
        Assertions.assertEquals(response.getStatus(), HTTP_CREATED);
    }

}
