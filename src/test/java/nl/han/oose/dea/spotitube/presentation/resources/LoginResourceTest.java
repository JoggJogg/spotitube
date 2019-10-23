package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.domain.pojo.User;
import nl.han.oose.dea.spotitube.domain.services.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class LoginResourceTest {

    private LoginResource sut;
    private AuthenticationService mockedService;
    private User user;

    @BeforeEach
    public void setup() {
        sut = new LoginResource();
        user = new User();
        mockedService = Mockito.mock(AuthenticationService.class);
        sut.setItemService(mockedService);
    }

    @Test
    public void handleLoginSuccessReturnsCreatedStatusCode() {
        // Arrange
        Mockito.when(mockedService.correctLogin(user)).thenReturn(true);

        // Act
        Response response = sut.handleLogin(user);

        // Assert
        Assertions.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void handleLoginFailReturnsBadRequestStatusCode() {
        // Arrange
        Mockito.when(mockedService.correctLogin(user)).thenReturn(false);

        // Act
        Response response = sut.handleLogin(user);

        // Assert
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void handleLoginCallsCorrectLoginMethod() {
        // Arrange

        // Act
        sut.handleLogin(user);

        // Assert
        Mockito.verify(mockedService).correctLogin(user);
    }

}
