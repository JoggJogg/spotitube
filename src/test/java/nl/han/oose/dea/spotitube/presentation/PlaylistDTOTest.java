package nl.han.oose.dea.spotitube.presentation;

import nl.han.oose.dea.spotitube.domain.DomainObject;
import nl.han.oose.dea.spotitube.domain.Playlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDTOTest {

    private PlaylistsDTO sut;
    private Playlist mockedPlaylist;
    private List<DomainObject> list;

    private static final int MOCKED_DURATION = 123;

    @Test
    public void getPlaylistsReturnsCorrectList() {
        // Arrange
        mockedPlaylist = Mockito.mock(Playlist.class);
        list = new ArrayList<>();
        list.add(mockedPlaylist);
        sut = new PlaylistsDTO(list, MOCKED_DURATION);

        // Act
        List<Playlist> newList = sut.getPlaylists();

        // Assert
        Assertions.assertTrue(newList.equals(list));
    }

    @Test
    public void getSecondsReturnsCorrectInt() {
        // Arrange
        mockedPlaylist = Mockito.mock(Playlist.class);
        list = new ArrayList<>();
        list.add(mockedPlaylist);
        sut = new PlaylistsDTO(list, MOCKED_DURATION);

        // Act
        int duration = sut.getDuration();

        // Assert
        Assertions.assertEquals(duration, MOCKED_DURATION);
    }

}
