package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

import java.awt.*;

public class Controller {

    private Boolean unlocked = false;
    private int numberOfTries = 3;
    private static String code = "";
    private boolean warned = false;
    private MediaPlayer audios;
    private static final String clickBtn = "sample/click.mp3";

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button btn0;
    @FXML
    private ImageView red;
    @FXML
    private ImageView green;

    @FXML
    public void handleClick(ActionEvent e){
        if (numberOfTries > 0) {
            green.setVisible(false);
            red.setVisible(false);
            String button = ((Button) e.getSource()).getText();
            int buttonNumber = Integer.parseInt(button);
            System.out.println(buttonNumber);
            playSound(1);
            if (numberOfTries == 1) {
                alarm();
                recieveCode(buttonNumber);
            } else {
                recieveCode(buttonNumber);
            }
        }
        else {
            red.setVisible(true);
            System.out.println("The Keypad is locked. Standby until security arrives");
        }
    }

    private void playSound(int codeaudio){
        AudioClip soundClick = new AudioClip(this.getClass().getResource("click.mp3").toString());
        AudioClip soundUnlock = new AudioClip(this.getClass().getResource("unlocked.mp3").toString());
        AudioClip soundLock = new AudioClip(this.getClass().getResource("locked.mp3").toString());
        if (codeaudio == 1){
            soundClick.play();
        } else if (codeaudio == 2){
            soundUnlock.play();
        }
        else soundLock.play();
    }


    private void recieveCode(int number){
        code += Integer.toString(number);
        verifycode(code);
    }

    private void verifycode(String codeToVerify){
        String correctCode = "5555";
        if (code.length() == 4){
            if (code.equals(correctCode)){
                System.out.println("Unlocked door!");
                unlocked = true;
                green.setVisible(true);
                code = "";
                playSound(2);
            } else {
                numberOfTries -= 1;
                if (numberOfTries == 0){
                    playSound(3);
                    System.out.println("keypad is locked");
                    red.setVisible(true);
                } else
                    System.out.println("try again");
                    code = "";
                }
            }
    }

    private void alarm(){

        if (numberOfTries == 1){
            if (warned != true){
                System.out.println("one try left");
            }

            warned = true;
            if (warned && numberOfTries == 1){

            }
        }
        else System.out.println("Calling the cops. Keypad Locked.");
    }

}
