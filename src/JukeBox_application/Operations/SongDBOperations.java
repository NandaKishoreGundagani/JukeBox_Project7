package JukeBox_application.Operations;

import JukeBox_application.Songs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDBOperations {
    String url = "jdbc:mysql://localhost:3306/jukebox";
    String user = "root";
    String pass = "Nanda@1997";

    Connection dbconnection;

    public SongDBOperations() {
        try {
            dbconnection = DriverManager.getConnection(url, user, pass);
           // System.out.println("Song Database connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addsongs(Songs song) {
        boolean result = false;
        //it will add songs to jukebox by taking values from the user
        try {
            int artistID = checkAndinsertArtist(song.getArtistName());
            // checks through the method checkAndinsertArtist
            // artistId is auto generated by the db by checking the name of artist given by user
            int genreId = checkAndInsertGenre(song.getGenreName());
            // checks through the method checkAndInsertGenre
            // and also generates and inserts genreId by checking the user given data

            PreparedStatement p1 = dbconnection.prepareStatement("Insert into songs(paths,songName,album,genreID,artistID,Duration) values (?,?,?,?,?,?)");
            p1.setString(1,song.getPaths());
        p1.setString(2, song.getSongName());
            p1.setString(3, song.getAlbum());
            p1.setInt(4, genreId);
            p1.setInt(5, artistID);
            p1.setString(6, song.getDuration());
            int rowsAffected = p1.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
                System.out.println("Song added sucessfully");
            } else
                result=false;
            // it will trow exception if any mistake in the sql query
        } catch (SQLException e) {
            e.printStackTrace();
        }  return result;
        // it returns the boolean value if true it shows song added sucess
    }

    int checkAndinsertArtist(String artist) throws SQLException {
        // it will check the artist if already exists or not if exsits it
        // gives the artist id and if not it will insert it and provide the id
        int artistId;
        PreparedStatement ps = dbconnection.prepareStatement("select * from artist where artistName=?");
        ps.setString(1, artist);
        ResultSet result = ps.executeQuery();
        if (result.next()) {
            artistId = result.getInt(1);
        } else {
            artistId = insertArtist(artist);
        }
        return artistId;
    }

    int insertArtist(String artistName) {
        int artistID = 0;
        try {
            PreparedStatement p2 = dbconnection.prepareStatement("insert into artist(artistName) values(?)", Statement.RETURN_GENERATED_KEYS);
            p2.setString(1, artistName);
            int rowsAffected = p2.executeUpdate();
            // int will insert the user given data into artist table
            if (rowsAffected > 0) {
                System.out.println("Artist Inserted");
                ResultSet results = p2.getGeneratedKeys();
                if (results.next()) {
                    artistID = results.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistID;
    }

    int checkAndInsertGenre(String genreName) throws SQLException {
        //this method  check the genre is exsits or not if exsits it will give the
        // it will takes to the insert genre
        int genreId;
        PreparedStatement ps = dbconnection.prepareStatement("select * from genre where genere=?");
        ps.setString(1, genreName);
        ResultSet result = ps.executeQuery();
        if (result.next()) {
            genreId = result.getInt(1);
        } else {
            genreId = insertGenre(genreName);
        }
        return genreId;

    }

    int insertGenre(String genreName) {
        int genreId = 0;
        try {
            PreparedStatement ps1 = dbconnection.prepareStatement("Insert into genre(genere) values(?)", Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, genreName);
            int rowsAffected = ps1.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Genre Inserted");
                ResultSet results = ps1.getGeneratedKeys();
                if (results.next()) {
                    genreId = results.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genreId;
    }
    public List<Songs> fetchSongs() throws SQLException {
        // it will create a list of songs
        List<Songs> songList = new ArrayList<>();
        Statement Song=dbconnection.createStatement();
        ResultSet resultSet=Song.executeQuery("select * from SongView");
        while (resultSet.next())
        {
            songList.add(new Songs(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));

        }
        return songList;
 }
    public int fetchSongIdBySongName(String songName)throws SQLException{
        // this method helps to search songId by song name
        int SongId=0;
        PreparedStatement preparedStatement=dbconnection.prepareStatement("select songId from Songs where songName=?");
        preparedStatement.setString(1,songName);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next())

            SongId=resultSet.getInt(1);

        return SongId;
    }
    public int fetchSongIdByAlbumName(String songAlbum)throws SQLException{
        // to search songId by albumname
        int SongId=0;
        String query="select songId from Songs where Album=?";
        PreparedStatement preparedStatement=dbconnection.prepareStatement(query);
        preparedStatement.setString(1,songAlbum);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next())
            SongId=resultSet.getInt(1);
        return SongId;
    }
}





