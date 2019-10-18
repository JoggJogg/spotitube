package nl.han.oose.dea.spotitube.domain;

public class Playlist extends DomainObject {
    private long id;
    private String name;
    private boolean owner;
    private Track [] tracks;

    public Playlist() {

    }

    public Playlist(long id, String name, boolean owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = new Track[]{};
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getOwner() {
        return owner;
    }

    public Track[] getTracks() {
        return tracks;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }
}
