package JukeBox_application.Operations;

import JukeBox_application.PlayList;
import JukeBox_application.Podcast;
import JukeBox_application.Songs;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayListOperatons {

    String url = "jdbc:mysql://localhost:3306/jukebox";
    String user = "root";
    String pass = "Nanda@1997";

    Connection dbconnection;

    public PlayListOperatons() {
        try {
            dbconnection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PlayList> filterPlaylistByName(ArrayList<PlayList> allPlaylist, String playlistName)throws SQLException{
        List<PlayList> playlists=allPlaylist.stream().filter(playlist -> playlist.getPlaylistName().equalsIgnoreCase(playlistName)).collect(Collectors.toList());
         System.out.println("______________________________________");
        System.out.printf("%20s ","PLAYLIST NAME");
        for (PlayList playlist:playlists){
            System.out.printf("%20s",playlist.getPlaylistName());
            System.out.println();
        }
        System.out.println("______________________________________");
        return playlists;
    }
    public void displayAllPlaylist(List<PlayList> playlist){
        if(playlist.isEmpty()){
            System.out.println("LIST IS EMPTY ");
        }
        else {
            System.out.println(" ALL PLAYLIST YOU HAVE ");
            System.out.println("______________________________________");
            System.out.format("%20s %20s \n ","PlaylistId","PlayListName");
            for (PlayList playlist1 : playlist) {
                System.out.printf("%20s %20s \n",playlist1.getPlaylistId(),playlist1.getPlaylistName());
            }
            System.out.println("______________________________________");
        }
    }

    public List<Integer> fetchTrackId(int playlistId)throws SQLException{
        List<Integer> trackIdList=new ArrayList<>();
        PreparedStatement preparedStatement=dbconnection.prepareStatement("select trackId from playlistcontents where playlistId=?");
        preparedStatement.setInt(1,playlistId);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next())
        {
            trackIdList.add(resultSet.getInt(1));
        }
        return trackIdList;
    }
    public  List<Songs> fetchSongByGiveTrackId(List<Integer> trackId) throws SQLException
    {
        List<Songs> songList=new ArrayList<>();
        for (int i = 0; i < trackId.size(); i++){
            PreparedStatement preparedStatement=dbconnection.prepareStatement
                    ("select songName,Album,genere,artistName,Duration from Songs join Genre on Songs.genreId=Genre.genreId join Artist on Artist.artistId=Songs.artistId  where songId=?");
            preparedStatement.setInt(1, trackId.get(i));
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next())
            {
                songList.add(new Songs(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5), resultSet.getString(6)));
            }
        }return songList;
    }

    public List<Podcast> fetchPodcastByGiveTrackId (List<Integer> trackId) throws SQLException {
        List<Podcast> podcastList=new ArrayList<>();
        for (int i=0;i<trackId.size();i++){
            PreparedStatement preparedStatement=dbconnection.prepareStatement
                    ("select podcatName,ReleaseDate,durationt_time,celebrityName from Celebrity join Podcast on Podcast.celebId=Celebrity.celebId where podcastId=?");
            preparedStatement.setInt(1,trackId.get(i));
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next())
            {
                podcastList.add(new Podcast(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
            }
        }
        return podcastList;
    }
}

