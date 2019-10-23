package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.domain.services.PlaylistService;
import nl.han.oose.dea.spotitube.presentation.resources.PlaylistResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlaylistResourceTest {

    private PlaylistResource sut;
    private PlaylistService mockedService;

    private static final String MOCK_TOKEN = "ABC";

    @BeforeEach
    public void setup() {
        sut = new PlaylistResource();
    }

    @Test
    public void getAllPlaylistsCallsFindAll() {
        // Arrange
        mockedService = Mockito.mock(PlaylistService.class);
        sut.setItemService(mockedService);
        // Act
        sut.getAllPlaylists(MOCK_TOKEN);
        // Assert
        Mockito.verify(mockedService).findAll();
    }
}
