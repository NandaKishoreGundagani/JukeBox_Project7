package JukeBox_application.Operations;

import JukeBox_application.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SongListOPeration {


        List<Songs> allSongList=new ArrayList<>();
        public List<Songs> filterSongByName(ArrayList<Songs> allSong,String songName)throws SQLException {
            Predicate<Songs> bySongName=(song)->song.getSongName().equalsIgnoreCase(songName);
            allSongList=(ArrayList<Songs>)allSong.stream().filter(bySongName).collect(Collectors.toList());
            return allSongList;
        }
        public List<Songs> filterSongByAlbum(ArrayList<Songs> allSong,String songAlbum)throws SQLException{
            Predicate<Songs> bySongAlbum=(album)->album.getAlbum().equalsIgnoreCase(songAlbum);
            allSongList=(ArrayList<Songs>)allSong.stream().filter(bySongAlbum).collect(Collectors.toList());
            return allSongList;
        }
        public List<Songs> filterSongByGenere(ArrayList<Songs> allSong,String songGenere)throws SQLException{
            Predicate<Songs> bySongGenere=(genere)->genere.getGenreName().equalsIgnoreCase(songGenere);
            allSongList=(ArrayList<Songs>)allSong.stream().filter(bySongGenere).collect(Collectors.toList());
            return allSongList;
        }
        public List<Songs> filterByArtist(ArrayList<Songs> allSong,String songArtist)throws SQLException{
            Predicate<Songs> bySongArtist=(artist)->artist.getArtistName().equalsIgnoreCase(songArtist);
            allSongList=(ArrayList<Songs>)allSong.stream().filter(bySongArtist).collect(Collectors.toList());
            return allSongList;
        }

        public void  displayAllSong(List<Songs>songList){
            if (songList.isEmpty()){
                System.out.println("LIST IS EMPTY");
            }else {

                System.out.println("==================================================================================");
                System.out.format("%20s  %20s  %20s  %20s  %20s  \n","SONGNAME","ALBUM","GENERE","ARTIST","DURATION");
                 System.out.println("==================================================================================");

                for (Songs song:songList){
                    System.out.printf("%20s  %20s  %20s  %20s  %20s \n" ,song.getSongName(),song.getAlbum(),song.getGenreName(),song.getArtistName(),song.getDuration());
                   // System.out.println();
                }

            }
        }
    }

