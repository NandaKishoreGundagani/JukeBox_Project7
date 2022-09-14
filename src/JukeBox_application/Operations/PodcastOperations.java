package JukeBox_application.Operations;

import JukeBox_application.Podcast;
import JukeBox_application.Songs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PodcastOperations {
    String url = "jdbc:mysql://localhost:3306/jukebox";
    String user = "root";
    String pass = "Nanda@1997";

    Connection dbconnection;

    public PodcastOperations() {
        try {
            dbconnection = DriverManager.getConnection(url, user, pass);
            //System.out.println("Podcast Database connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addPodcast(Podcast podcast) {
        boolean result = false;
        try {
            int CelebId = CheckAndInsertPodcast(podcast.getCelebrityName());
            PreparedStatement p11 = dbconnection.prepareStatement("Insert into podcast(podcatName,releaseDate,CelebId,durationt_time) values (?,?,?,?)");
            p11.setString(1, podcast.getPodcastName());
            p11.setString(2, podcast.getReleaseDate());
            p11.setInt(3, CelebId);
            p11.setString(4, podcast.getDurationTime());
            int rowsAffected = p11.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
                System.out.println("Podcast added sucessfully");
            } else
                result = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    int CheckAndInsertPodcast(String celebName) throws SQLException {
        int CelebId = 0;
        PreparedStatement ps = dbconnection.prepareStatement("select * from celebrity where celebrityName=?");
        ps.setString(1, celebName);
        ResultSet result = ps.executeQuery();
        if (result.next()) {
            CelebId = result.getInt(1);
        } else {
            CelebId = insertCelebrity(celebName);
        }
        return CelebId;
    }

    int insertCelebrity(String celebName) {
        int CelebID = 0;
        try {
            PreparedStatement p2 = dbconnection.prepareStatement("insert into celebrity(CelebrityName) values(?)", Statement.RETURN_GENERATED_KEYS);
            p2.setString(1, celebName);
            int rowsAffected = p2.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Celebrity Inserted");
                ResultSet results = p2.getGeneratedKeys();
                if (results.next()) {
                    CelebID = results.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CelebID;
    }

    public List<Podcast> fetchPodcast()  {
        List<Podcast> podcastList = new ArrayList<>();
        try {

            Statement podcast = dbconnection.createStatement();
            ResultSet resultSet = podcast.executeQuery("select * from Podcastview");
            while (resultSet.next()) {
                podcastList.add (new Podcast(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return podcastList;
    }
    public int fetchPodcastID(String podcastName)throws SQLException{
        int podcastId=0;
        String query="select podcastId from Podcast where podcatName=?";
        PreparedStatement preparedStatement=dbconnection.prepareStatement(query);
        preparedStatement.setString(1,podcastName);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next())
            podcastId = resultSet.getInt(1);
        return podcastId;
    }
}

