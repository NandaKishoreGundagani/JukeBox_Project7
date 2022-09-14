package JukeBox_application.Operations;

import JukeBox_application.Podcast;
import JukeBox_application.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PodcastListOperations  {

    List<Podcast> allpodcastList=new ArrayList<>();


    public List<Podcast> filterPodcastByName(ArrayList<Podcast> allpodcastList, String podcastName)throws SQLException {
        Predicate<Podcast> byPodcastName=(podcast)->podcast.getPodcastName().equalsIgnoreCase(podcastName);
        allpodcastList=(ArrayList<Podcast>)allpodcastList.stream().filter(byPodcastName).collect(Collectors.toList());
        return allpodcastList;
    }
    public List<Podcast> filterPodcastByCelebrity(ArrayList<Podcast> allpodcastList, String celebrityName)throws SQLException {
        Predicate<Podcast> byPodcastCelebrityName=(podcast)->podcast.getCelebrityName().equalsIgnoreCase(celebrityName);
        allpodcastList=(ArrayList<Podcast>)allpodcastList.stream().filter(byPodcastCelebrityName).collect(Collectors.toList());
        return allpodcastList;
    }

    public static void displayAllpodcasts(List<Podcast> podcastList) {
        if (podcastList.isEmpty()) {
            System.out.println("LIST IS EMPTY");
        } else {
            System.out.println("=======================================================================================");
            System.out.format("%20s  %20s  %20s  %20s \n", "PODCASTNAME", "CELEBRITY NAME", "DURATION", "RELEASE DATE");
            System.out.println("=========================================================================================");

            for (Podcast podcast : podcastList) {
                System.out.printf("%20s  %20s  %20s  %20s \n ", podcast.getPodcastName(), podcast.getCelebrityName(), podcast.getDurationTime(), podcast.getReleaseDate());
                //System.out.println("________________________________________________________________________________________");
            }

        }

    }
    }


