package nl.han.oose.dea.spotitube;

import com.mysql.cj.log.Log;
import nl.han.oose.dea.spotitube.datasource.LoginDAO;
import nl.han.oose.dea.spotitube.domain.UserDTO;
import nl.han.oose.dea.spotitube.remote.LoginResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class LoginResourceTest {

    private LoginResource sut;
    private UserDTO mockedUser;

    private static final int HTTP_CREATED = 201;

    @BeforeEach
    public void setup() {
        sut = new LoginResource();
    }

    @Test
    public void handleLoginSuccesReturns201StatusCode() {
        // Arrange
        mockedUser = Mockito.mock(UserDTO.class);
        // Act
        Response response = sut.handleLogin(mockedUser);
        // Assert
        Assertions.assertEquals(response.getStatus(), HTTP_CREATED);
    }

}
