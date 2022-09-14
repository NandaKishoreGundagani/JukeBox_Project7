package JukeBox_application;

public class Podcast {
   private String srcpaths;
   private String podcastName;
    private String celebrityName;
    private String durationTime;
    private   String releaseDate;

    public Podcast(String paths, String podcastName, String celebrityName, String durationTime, String releaseDate) {
        this.srcpaths = paths;
        this.podcastName = podcastName;
        this.celebrityName = celebrityName;
        this.durationTime = durationTime;
        this.releaseDate = releaseDate;
    }

    public String getPaths() {
        return srcpaths;
    }

    public void setPaths(String paths) {
        this.srcpaths = paths;
    }

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public String getCelebrityName() {
        return celebrityName;
    }

    public void setCelebrityName(String celebrityName) {
        this.celebrityName = celebrityName;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "paths='" + srcpaths + '\'' +
                ", podcastName='" + podcastName + '\'' +
                ", celebrityName='" + celebrityName + '\'' +
                ", durationTime='" + durationTime + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}








