package es.upm.pproject.sokoban.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class MusicView{
    private static MediaPlayer mediaPlayer;
 
    private MusicView(){
    }

    public static void start(String musicURL) {
        double volume = (mediaPlayer != null) ? mediaPlayer.getVolume() : 1.0;
        Media media = new Media(musicURL);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

public static void stop(){
    if(mediaPlayer!=null) mediaPlayer.pause();
}
public static void turnUp(){
    double volumen = mediaPlayer.getVolume();
    if((volumen) < 1.0) mediaPlayer.setVolume(volumen + 0.1); 
}
public static void turnDown(){
    double volumen = mediaPlayer.getVolume();
    if((volumen) > 0.0) mediaPlayer.setVolume(volumen - 0.1); 
}


}
