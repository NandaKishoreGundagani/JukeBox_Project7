package JukeBox_application;

public class Artists {
    int artistId;
    String artistName;

    public Artists(int artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "Artists{" +
                "atistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
