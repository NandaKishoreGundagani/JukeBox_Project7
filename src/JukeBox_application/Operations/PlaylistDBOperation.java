package JukeBox_application.Operations;

import JukeBox_application.PlayList;
import JukeBox_application.Podcast;
import JukeBox_application.Songs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDBOperation {
    String url = "jdbc:mysql://localhost:3306/jukebox";
    String user = "root";
    String pass = "Nanda@1997";

    Connection dbconnection;

    public PlaylistDBOperation() {
        try {
            dbconnection = DriverManager.getConnection(url, user, pass);
           // System.out.println("playlist Database connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addPlaylist(PlayList playlist) throws SQLException {
        Boolean result = false;
        int playlistId = checkAndInsertPlaylist(playlist.getPlaylistName());
        PreparedStatement preparedStatement = dbconnection.prepareStatement("insert into Playlist(playlistId,playlistName) values (?,?)");
        preparedStatement.setInt(1, playlistId);
        preparedStatement.setString(2, playlist.getPlaylistName());
        int playlistInserted = preparedStatement.executeUpdate();
        if (playlistInserted > 0) {
            result = true;
            System.out.println("SUCCESFULL");
        } else {
            result = false;
            System.out.println("UNSUCCESFULL");
        }
        return result;
    }
    public int checkAndInsertPlaylist(String playlistName) throws SQLException {
         int playlisdId=0;
        PreparedStatement preparedStatement = dbconnection.prepareStatement("Select * from Playlist where playlistName=?");
        preparedStatement.setString(1, playlistName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            playlisdId = resultSet.getInt(1);
        return playlisdId;
    }

    public List<PlayList> fetchPlaylist() throws SQLException {
        List<PlayList> playlists = new ArrayList<>();
        Statement Playlist = dbconnection.createStatement();
        ResultSet resultSet = Playlist.executeQuery("select * from Playlist");
        while (resultSet.next()) {
            playlists.add(new PlayList(resultSet.getInt(1), resultSet.getString(2)));
        }
        return playlists;
    }


    public int fetchPlayListId(String playlistName) throws SQLException {
        int playlistId=0;
        PreparedStatement preparedStatement = dbconnection.prepareStatement("select playlistId from Playlist where playlistName=?");
        preparedStatement.setString(1, playlistName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            playlistId = resultSet.getInt(1);
        return playlistId;

    } public boolean makeNewTrackId(int playlistId,int givenId)throws SQLException{
        boolean result= false;
        PreparedStatement preparedStatement=dbconnection.prepareStatement
                ("insert into PlaylistContents(playlistId,trackId) values(?,?)");
        preparedStatement.setInt(1,playlistId);
        preparedStatement.setInt(2,givenId);
        int rowsAffected=preparedStatement.executeUpdate();
        if(rowsAffected>0){
            result=true;
            System.out.println(" SUCCESFULL");
        }else {
            result=false;
        }
        return result;
    }
    public void  displayAllSongInGivenTrackId(List<Songs>songList){
        if (songList.isEmpty()){
            System.out.println("LIST IS EMPTY");
        }else {

            System.out.format("%20s %20s %20s %20s %20s \n","SONGNAME","ALBUM" ,"GENRE","ARTIST","DURATION" );

          //  System.out.println("===========================================================================================");
            for (Songs song:songList){
                System.out.printf("%20s %20s %20s %20s %20s ",song.getSongName(),song.getAlbum(),song.getGenreName(),song.getArtistName(),song.getDuration());
                System.out.println();
            }
        }
    }

    public  void  displayAllPodcastInGivenTrackId(List<Podcast> podcastList){
        if (podcastList.isEmpty()){
            System.out.println("PODCAST  LIST IS EMPTY");
        }else {
            System.out.println("PODCAST IN SELECTED PLAYLIST ");
            System.out.println("______________________________________________________");
            System.out.format("%20s %20s %20s  \n","PODCAST","RELEASE_DATE" ,"CELEBRITY");
            System.out.println("=======================================================");
            for (Podcast podcast:podcastList){
                System.out.printf("%20s %20s  %20s  \n",podcast.getPodcastName(),podcast.getCelebrityName(),podcast.getReleaseDate());
            }
            System.out.println("____________________________________________________________");
        }
    }

}
