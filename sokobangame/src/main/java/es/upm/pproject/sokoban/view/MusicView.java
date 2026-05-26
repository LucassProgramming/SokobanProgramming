package es.upm.pproject.sokoban.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class MusicView{
    private Media media;
    private static MediaPlayer mediaPlayer;
    private String musicURL;
 
    public MusicView(String path){
        musicURL = getClass().getResource(path).toExternalForm();
    }

    public void start() {

    // Crear el objeto Media
    media = new Media(musicURL);

    // Crear y reproducir el MediaPlayer
   mediaPlayer = new MediaPlayer(media);
   mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
   mediaPlayer.setVolume(0.3);
    mediaPlayer.play();
}

public static void stop(){
    if(mediaPlayer!=null) mediaPlayer.pause();
}


}
