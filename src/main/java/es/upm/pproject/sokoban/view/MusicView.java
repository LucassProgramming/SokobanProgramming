package es.upm.pproject.sokoban.view;

import java.util.logging.Logger;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class MusicView{
    private static final Logger LOGGER = Logger.getLogger(MusicView.class.getName());
    private static MediaPlayer mediaPlayer;
 
    private MusicView(){
    }

    public static void start(String musicURL) {
        try {
            double volume = (mediaPlayer != null) ? mediaPlayer.getVolume() : 1.0;
            Media media = new Media(musicURL);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volume);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (RuntimeException e) {
            mediaPlayer = null;
            LOGGER.warning("No se pudo iniciar la musica");
        }
    }

    public static void stop(){
        if(mediaPlayer!=null) mediaPlayer.pause();
    }

    public static void dispose(){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
    }

    public static void turnUp(){
        if(mediaPlayer!=null){
            double volumen = mediaPlayer.getVolume();
            if((volumen) < 1.0) mediaPlayer.setVolume(volumen + 0.1);
        }
    }

    public static void turnDown(){
        if(mediaPlayer!=null){
            double volumen = mediaPlayer.getVolume();
            if((volumen) > 0.0) mediaPlayer.setVolume(volumen - 0.1);
        }
    }


}
