package JukeBox_application;

    public class PlayList {
        private int PlaylistId;
        private String playlistName;

        public int getPlaylistId() {
            return PlaylistId;
        }

        public void setPlaylistId(int playlistId) {
            PlaylistId = playlistId;
        }

        public String getPlaylistName() {
            return playlistName;
        }

        public void setPlaylistName(String playlistName) {
            this.playlistName = playlistName;
        }

        public PlayList(int playlistId, String playlistName) {
            PlaylistId = playlistId;
            this.playlistName = playlistName;

        }

        @Override
        public String toString() {
            return "Playlist{" +
                    "PlaylistId=" + PlaylistId +
                    ", playlistName='" + playlistName + '\'' +
                    '}';
        }
    }


