package nl.han.oose.dea.spotitube.presentation;

import nl.han.oose.dea.spotitube.data.PlaylistDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlaylistServiceTest {

    private PlaylistResource sut;
    private PlaylistDataMapper mockedDataMapper;

    private static final String MOCK_TOKEN = "ABC";

    @BeforeEach
    public void setup() {
        sut = new PlaylistResource();
    }

    @Test
    public void getAllPlaylistsCallsFindAll() {
        // Arrange
        mockedDataMapper = Mockito.mock(PlaylistDataMapper.class);
        sut.setDataMapper(mockedDataMapper);
        // Act
        sut.getAllPlaylists(MOCK_TOKEN);
        // Assert
        Mockito.verify(mockedDataMapper).findAll();
    }
}
