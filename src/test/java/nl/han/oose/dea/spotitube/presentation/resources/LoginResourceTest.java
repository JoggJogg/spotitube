package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.domain.pojo.User;
import nl.han.oose.dea.spotitube.domain.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoginResourceTest {

    private LoginResource sut;
    private AuthenticationService mockedService;
    private User user;

    @BeforeEach
    public void setup() {
        sut = new LoginResource();
        user = new User();
        mockedService = Mockito.mock(AuthenticationService.class);
        sut.setAuthenticationService(mockedService);
    }

    @Test
    public void handleLoginCallsCorrectLoginMethod() {
        // Arrange

        // Act
        sut.handleLogin(user);

        // Assert
        Mockito.verify(mockedService).login(user);
    }

}
