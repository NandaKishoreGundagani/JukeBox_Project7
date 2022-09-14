package JukeBox_application;

public class Songs {
    private String paths;
    private String songName;
    private String album;
    private String genreName;
    private String artistName;
    private String duration;

    public Songs(String paths, String songName, String album, String genreName, String artistName, String duration) {
        this.paths = paths;
        this.songName = songName;
        this.album = album;
        this.genreName = genreName;
        this.artistName = artistName;
        this.duration = duration;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "paths='" + paths + '\'' +
                ", songName='" + songName + '\'' +
                ", album='" + album + '\'' +
                ", genreName='" + genreName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}


