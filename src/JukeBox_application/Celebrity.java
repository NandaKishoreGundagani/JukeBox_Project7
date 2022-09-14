package JukeBox_application;

public class Celebrity {
    int CelebId;
    String celebrityName;

    public Celebrity(int celebId, String celebrityName) {
        CelebId = celebId;
        this.celebrityName = celebrityName;
    }

    public int getCelebId() {
        return CelebId;
    }

    public void setCelebId(int celebId) {
        CelebId = celebId;
    }

    public String getCelebrityName() {
        return celebrityName;
    }

    public void setCelebrityName(String celebrityName) {
        this.celebrityName = celebrityName;
    }

    @Override
    public String toString() {
        return "Celebrity{" +
                "CelebId=" + CelebId +
                ", celebrityName='" + celebrityName + '\'' +
                '}';
    }
}
