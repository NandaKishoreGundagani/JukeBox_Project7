package mainJukeBox;

import JukeBox_application.Operations.*;
import JukeBox_application.PlayList;
import JukeBox_application.Podcast;
import JukeBox_application.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JukeBoxMain {


    public static void main(String[] args) {
        try {

            SongDBOperations songDBOperations = new SongDBOperations();
            PodcastOperations operations = new PodcastOperations();
            SongListOPeration songListOPeration = new SongListOPeration();
            PodcastListOperations podcastListOperations = new PodcastListOperations();
            PodcastOperations operations1=new PodcastOperations();
            PlaylistDBOperation playlistDBOperation = new PlaylistDBOperation();
            PlayListOperatons playListOperatons = new PlayListOperatons();
            List<Songs> songsList;
            List<Podcast> podcastList;
            List<PlayList> playList;
            AudioPlayer audioPlayer=new AudioPlayer();
            Scanner scanner = new Scanner(System.in);

            int start = 1;

            do {
                songsList= songDBOperations.fetchSongs();
                podcastList= operations.fetchPodcast();
                playList= playlistDBOperation.fetchPlaylist();
                System.out.println("WELCOME TO MUSIC JUKEBOX");
                System.out.println("WHAT YOU WANT TO DO?");
                System.out.println("1.play music");
                System.out.println("2.To Add Songs");
                System.out.println("3.To Add  Podcast");
                System.out.println("4.Display all Song");

                System.out.println("5.Display all Podcasts");
                System.out.println("6.Playlist");
                System.out.println("7.Search Song or podcast");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:

                        break;
                    case 2:
                        int songID = 0;
                        System.out.println("paste song folder path");
                        String filepath= scanner.next();
                        System.out.println("enter song name");
                        String songName = scanner.next();
                        System.out.println("enter AlbumName");
                        String album = scanner.next();
                        System.out.println("Enter genre");
                        String genreName = scanner.next();
                        System.out.println("enter artist");
                        String artistName = scanner.next();
                        System.out.println("enter Duration");
                        String duration = scanner.next();
                        Songs song = new Songs(filepath,songName, album, genreName, artistName, duration);
                        songDBOperations.addsongs(song);
                        break;
                    case 3:
                        System.out.println("paste podcast path");
                        String srcpaths= scanner.next();
                        System.out.println("Enter Podcast Name");
                        String podcastName = scanner.next();
                        System.out.println("enter release Date");
                        String releaseDate = scanner.next();
                        System.out.println("Enter Celebrity Name");
                        String celebrityName = scanner.next();
                        System.out.println("Enter Duration");
                        String durationTime = scanner.next();
                        Podcast podcast = new Podcast(srcpaths,podcastName, celebrityName, releaseDate, durationTime);
                        operations.addPodcast(podcast);
                        break;
                    case 4:
                        songListOPeration.displayAllSong(songsList);
                        break;
                    case 5:
                        PodcastListOperations.displayAllpodcasts(podcastList);
                        break;
                    case 6:
                        System.out.println("1.To create Playlist");
                        System.out.println("2.Display PlayList");
                        System.out.println("3.Add Song or podcast To PlayList");
                        System.out.println("4.search playlist");
                        int choice5 = scanner.nextInt();
                        switch (choice5) {
                            case 1:
                                System.out.println("Enter Playlist Name To be given");
                                String playlistName = scanner.next();
                                int playlistId = playlistDBOperation.fetchPlayListId(playlistName);
                                PlayList playList1 = new PlayList(playlistId, playlistName);
                                playlistDBOperation.addPlaylist(playList1);

                                break;
                            case 2:
                                playListOperatons.displayAllPlaylist(playList);
                                break;
                            case 3:

                                System.out.println("Enter playlist Name in which you want to add song or podcast");
                                String nameOfplaylist = scanner.next();
                                playlistId = playlistDBOperation.fetchPlayListId(nameOfplaylist);
                                if (playlistId != 0) {
                                    System.out.println("1. ADD SONG BY NAME ");
                                    System.out.println("2. ADD SONG BY ALBUM ");
                                    System.out.println("3.Add podcast");
                                    int chioicePlaylistAddSong = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (chioicePlaylistAddSong) {
                                        case 1:
                                            System.out.print("ENTER  SONG NAME YOU WANT TO ADD");
                                            String playlistSongName = scanner.next();
                                            int givenId = songDBOperations.fetchSongIdBySongName(playlistSongName);
                                            playlistDBOperation.makeNewTrackId(playlistId, givenId);
                                            break;
                                        case 2:
                                            System.out.print("ENTER ALBUM NAME YOU WANT TO ADD");
                                            String playlistAlbumName = scanner.next();
                                            int givenID = songDBOperations.fetchSongIdByAlbumName(playlistAlbumName);
                                            playlistDBOperation.makeNewTrackId(playlistId, givenID);
                                            break;
                                        case 3:
                                            System.out.println("ENTER THE PODCAST NAME");
                                            podcastName = scanner.next();
                                            givenID = operations1.fetchPodcastID(podcastName);
                                            playlistDBOperation.makeNewTrackId(playlistId, givenID);
                                            break;

                                    }
                                } else
                                    System.out.println("Playlisyt Not Found");
                                break;


                            case 4:
                                System.out.print("=======ENTER PLAYLIST NAME======");
                                String playListName = scanner.next();
                                playListOperatons.filterPlaylistByName((ArrayList<PlayList>) playList, playListName);
                                System.out.println("=============== ALL DATA IN SELECTED PLAYLIST============= ");
                                playlistId = playlistDBOperation.fetchPlayListId(playListName);
                                List<Integer> fetch = playListOperatons.fetchTrackId(playlistId);
                                playlistDBOperation.displayAllSongInGivenTrackId(playListOperatons.fetchSongByGiveTrackId(fetch));
                                playlistDBOperation.displayAllPodcastInGivenTrackId(playListOperatons.fetchPodcastByGiveTrackId(fetch));
                                break;
                        }

                        break;
                    case 7:
                        System.out.println("=======Select What to Search=======");
                        System.out.println("1.Search by song Name");
                        System.out.println("2.Search by Genre");
                        System.out.println("3.Search by Album");
                        System.out.println("4.Search by Artist");
                        System.out.println("5.Search by Podcast by Podcast Name");
                        System.out.println("6.Search Podcast by celebrity Name");

                        int choice2 = scanner.nextInt();
                        switch (choice2) {
                            case 1:
                                System.out.println("Search by song Name");
                                songName = scanner.next();
                                System.out.println(songListOPeration.filterSongByName((ArrayList<Songs>) songsList, songName));
                                break;
                            case 2:
                                System.out.println("Search by Genre");
                                genreName = scanner.next();
                                System.out.println(songListOPeration.filterSongByGenere((ArrayList<Songs>) songsList, genreName));
                                break;

                            case 3:
                                System.out.println("Search by Album");
                                album = scanner.next();
                                System.out.println(songListOPeration.filterSongByAlbum((ArrayList<Songs>) songsList, album));
                                break;

                            case 4:
                                System.out.println("Search By artist");
                                artistName = scanner.next();
                                System.out.println(songListOPeration.filterByArtist((ArrayList<Songs>) songsList, artistName));
                                break;
                            case 5:
                                System.out.println("Search by PodcastName");
                                podcastName = scanner.next();
                                System.out.println(podcastListOperations.filterPodcastByName((ArrayList<Podcast>) podcastList, podcastName));
                                break;
                            case 6:
                                System.out.println("Search Podcast by Celebrity Name");
                                celebrityName = scanner.next();
                                System.out.println(podcastListOperations.filterPodcastByCelebrity((ArrayList<Podcast>) podcastList, celebrityName));
                                break;
                        }
                        break;
                }


                System.out.println("Press 1 to Continue \n Press 0 to Exit");
                start = scanner.nextInt();
            } while (start == 1);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
