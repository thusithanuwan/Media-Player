package lk.ijse.dep10.media;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AppInitializer extends Application {

    MediaPlayer mediaPlayer;
    int x =0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mediaPlayer(primaryStage);

    }

    void mediaPlayer(Stage stage) {

        Button btnOpen = new Button("Open");
        TextField fldTrackName = new TextField();

        // Top VBox

        VBox vBox1 = new VBox(btnOpen, fldTrackName);
        vBox1.setSpacing(10);
        vBox1.setPadding(new Insets(10,10,10,10));

        // Top VBox Controls

        fldTrackName.setMaxWidth(Double.MAX_VALUE);




        //  Icon Image viewers

        ImageView imgPlay = new ImageView();
//        ImageView imgPause = new ImageView();
        ImageView imgStop = new ImageView();
        ImageView imgRepeat = new ImageView();
        ImageView imgMute = new ImageView();

        // Icon

        Image play = new Image("/imges/play-button .png");
        Image pause = new Image("/imges/pause .png");
        Image stop = new Image("/imges/stop-button.png");
        Image stop1 = new Image("/imges/stop-button (1).png");
        Image suffle = new Image("/imges/suffle.png");
        Image repeat = new Image("/imges/repeat .png");
        Image volume = new Image("/imges/volume .png");
        Image silent = new Image("/imges/silent.png");

        // set icon to the Image viewer's

        imgPlay.setImage(play);
//        imgPause.setImage(pause);
        imgStop.setImage(stop);
        imgRepeat.setImage(suffle);
        imgMute.setImage(volume);

        // set Image Viewers sizes

        imgPlay.setFitWidth(100);
        imgPlay.setFitHeight(100);
        imgPlay.setCursor(Cursor.HAND);

//        imgPause.setFitWidth(50);
//        imgPause.setFitHeight(50);
//        imgPause.setCursor(Cursor.HAND);

        imgStop.setFitWidth(40);
        imgStop.setFitHeight(40);
        imgStop.setCursor(Cursor.HAND);

        imgRepeat.setFitWidth(40);
        imgRepeat.setFitHeight(40);
        imgRepeat.setCursor(Cursor.HAND);

        imgMute.setFitWidth(40);
        imgMute.setFitHeight(40);
        imgMute.setCursor(Cursor.HAND);

        Slider sldrVolume = new Slider();


        //  image viewer's to set Hbox

        HBox hBox2 = new HBox(imgStop, imgRepeat, imgMute, sldrVolume);
        hBox2.setSpacing(20);
        hBox2.setAlignment(Pos.CENTER);



/*
                                                              button actions
*/

        // Play Button

        imgPlay.setOnMouseEntered(event -> imgPlay.setOpacity(0.8));
        imgPlay.setOnMouseExited(event -> imgPlay.setOpacity(1));
        imgPlay.setOnMousePressed(event -> imgPlay.setEffect(new InnerShadow(10, Color.BLACK)));
        imgPlay.setOnMouseReleased(event -> {
            imgPlay.setEffect(null);
//            mediaPlayer.play();
            if (mediaPlayer != null) {
                if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    imgPlay.setImage(play);
                    mediaPlayer.pause();
                } else {
                    imgPlay.setImage(pause);
                    imgStop.setImage(stop);
                    mediaPlayer.play();
                }



            }});

        // Pause Button

//        imgPause.setOnMouseEntered(event -> imgPause.setOpacity(0.8));
//        imgPause.setOnMouseExited(event -> imgPause.setOpacity(1));
//        imgPause.setOnMousePressed(event -> imgPause.setEffect(new InnerShadow(10, Color.BLACK)));
//        imgPause.setOnMouseReleased(event -> {
//            imgPause.setEffect(null);
//            mediaPlayer.pause();
//        });

        // Stop Button

        imgStop.setOnMouseEntered(event -> imgStop.setOpacity(0.8));
        imgStop.setOnMouseExited(event -> imgStop.setOpacity(1));
        imgStop.setOnMousePressed(event -> imgStop.setEffect(new InnerShadow(10, Color.BLACK)));
        imgStop.setOnMouseReleased(event -> {
            imgStop.setEffect(null);
            imgStop.setImage(stop1);
            imgPlay.setImage(play);
            imgRepeat.setImage(suffle);
            mediaPlayer.stop();
        });

        // Repeat Button

        imgRepeat.setOnMouseEntered(event -> imgRepeat.setOpacity(0.8));
        imgRepeat.setOnMouseExited(event -> imgRepeat.setOpacity(1));
        imgRepeat.setOnMousePressed(event -> imgRepeat.setEffect(new InnerShadow(10, Color.BLACK)));
        imgRepeat.setOnMouseReleased(event -> {
            imgRepeat.setEffect(null);

            if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
                mediaPlayer.setCycleCount(Integer.MAX_VALUE);
                imgRepeat.setImage(repeat);

            }


        });

        // Mute Button

        imgMute.setOnMouseEntered(event -> imgMute.setOpacity(0.8));
        imgMute.setOnMouseExited(event -> imgMute.setOpacity(1));
        imgMute.setOnMousePressed(event -> imgMute.setEffect(new InnerShadow(10, Color.BLACK)));
        imgMute.setOnMouseReleased(event -> {
            imgRepeat.setEffect(null);

            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                if (!mediaPlayer.isMute()){
                    mediaPlayer.setMute(true);
                    imgMute.setImage(silent);
                } else {
                    mediaPlayer.setMute(false);
                    imgMute.setImage(volume);
                }
            }

        });

        // Slider

        sldrVolume.setOnMouseEntered(event -> sldrVolume.setOpacity(0.8));
        sldrVolume.setOnMouseExited(event -> sldrVolume.setOpacity(1));
        sldrVolume.setShowTickLabels(true);

        sldrVolume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
//                x = (int) sldrVolume.getValue();

                if(mediaPlayer == null){
                    return;
                }
                mediaPlayer.setVolume((double) newValue / 100);
                System.out.println((double) newValue / 100);

            }
        });

















        btnOpen.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Open an audio file");
            File audioFile = fileChooser.showOpenDialog(null);
            fldTrackName.setText(audioFile.toString());

            if (audioFile != null){
                Media media = new Media(audioFile.toURI().toString());
                mediaPlayer = new MediaPlayer(media);

            }else{
                mediaPlayer = null;
            }
        });


        //

        VBox vBox2 = new VBox(vBox1,hBox2);

        //

        VBox vBox3 = new VBox(imgPlay);
        VBox.setMargin(imgPlay,new Insets(50,10,10,10));

        // root is the final pane

        HBox root = new HBox(vBox3, vBox2);
        LinearGradient gradient = new LinearGradient(
                0,
                0,
                1,
                1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0.1f, Color.LIGHTSKYBLUE),
                new Stop(1.0f, Color.LIGHTPINK)
        );

        root.setBackground(Background.fill(gradient));
        root.setSpacing(15);



        // stage





        stage.setResizable(false);
        stage.setTitle("TN Player");
        stage.setScene(new Scene(root));
        stage.setMinWidth(200);
        stage.setWidth(550);
        stage.setMinHeight(150);
        stage.setHeight(240);
        stage.show();
        stage.centerOnScreen();


    }
}
