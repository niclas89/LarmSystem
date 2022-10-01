package com.example.movingthief;

import House.House;
import com.example.Alarm.CentralUnit;
import com.example.Alarm.KeyPad;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class Controller implements Initializable{
    private CentralUnit cu = new CentralUnit();
    private House house = new House();
    private KeyPad keyPad = new KeyPad();

    private String id;
    private String type;

    private boolean timerTriggerd;
    private boolean sirenTriggerdGlobal;
    private int counter = 15;
    private int index;
    private Timer timer = new Timer();

    FadeTransition fade = new FadeTransition();

    File burglarAlarm = new File("src/main/java/com/example/res/Burglar-alarm-sound.wav");
    File fireAlarm = new File("C:\\Users\\Nicla\\IdeaProjects\\MovingThief\\src\\main\\java\\com\\example\\res\\fire.wav");

    private ArrayList<Rectangle> colliSionObjects = new ArrayList<>();



    @FXML
    private Rectangle B1D0;
    @FXML
    private Rectangle B1W0;
    @FXML
    private Rectangle B1W1;
    @FXML
    private Rectangle B2D0;
    @FXML
    private Rectangle B2W0;
    @FXML
    private Rectangle B3W0;
    @FXML
    private Rectangle B3D0;
    @FXML
    private Rectangle B4D0;
    @FXML
    private Rectangle B4W0;
    @FXML
    private Rectangle B4W1;
    @FXML
    private Rectangle B5D0;
    @FXML
    private Rectangle B5W0;

    @FXML
    private Rectangle G1D0;
    @FXML
    private Rectangle G1D1;
    @FXML
    private Rectangle G1W0;
    @FXML
    private Rectangle H1D0;
    @FXML
    private Rectangle H1W0;


    @FXML
    private Rectangle K1W0;
    @FXML
    private Rectangle L1D0;
    @FXML
    private Rectangle L1W0;
    @FXML
    private Rectangle L1W1;

    @FXML
    private Rectangle H1M0;
    @FXML
    private Rectangle L1M0;

    @FXML
    private Rectangle Y1M0;

    @FXML
    private TextField keyPadDisplay;
    @FXML
    private TextField countDownTf;
    @FXML
    private TextArea logTerminal;

    @FXML
    private ImageView Thief;
    @FXML
    private Circle LightFront;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cu.connectSystem(house.exportHouse());
        cu.deactiveAllSystem(2478);
        keyPad.setCu(cu); keyPad.setDisplayMessage();
        keyPadDisplay.setText(keyPad.getDisplayMessage());
        colliSionObjects.add(B1D0); colliSionObjects.add(B1W0); colliSionObjects.add(B2D0); colliSionObjects.add(B2W0);
        colliSionObjects.add(B3W0); colliSionObjects.add(B3D0); colliSionObjects.add(B4D0); colliSionObjects.add(B4W0);
        colliSionObjects.add(B4W1); colliSionObjects.add(B5D0); colliSionObjects.add(B5W0); colliSionObjects.add(K1W0);
        colliSionObjects.add(L1D0);  colliSionObjects.add(L1W0);  colliSionObjects.add(L1W1);  colliSionObjects.add(H1D0);
        colliSionObjects.add(H1W0);  colliSionObjects.add(G1D0);  colliSionObjects.add(G1D1);  colliSionObjects.add(G1W0);
        colliSionObjects.add(B1W1);  colliSionObjects.add(L1M0);  colliSionObjects.add(H1M0); colliSionObjects.add(Y1M0);



        fade.setNode(LightFront);
        fade.setDuration(Duration.millis(1000));
        fade.setCycleCount(Animation.INDEFINITE);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(1);
        fade.setToValue(0);
    }



    @FXML
    public void Move(KeyCode code) throws IOException {                // metod för styrning av tjuven
            collisionTest();


            switch (code) {
                case W -> { if(Thief.getY()-5 > -560)
                    Thief.setY(Thief.getY() - 5);}

                case S -> {if(Thief.getY()+5 < 100)
                    Thief.setY(Thief.getY() + 5);
                }
                case A -> {if(Thief.getX()-5 > -580)
                    Thief.setX(Thief.getX() - 5);}


                case D -> {if(Thief.getX() +5 < 30)
                    Thief.setX(Thief.getX() + 5);
                }

            }

    }



    public void lights()throws IOException {

        if (cu.isActive()&& cu.isTriggerd()) {
            fade.play();
        }else if(!cu.isActive()){
           fade.stop();
           LightFront.setOpacity(0);
        }



    }


    public void keyPadInput(ActionEvent event) throws IOException {            // Tar emot input på keypaden på GUI och skickar
        String button = event.getSource().toString().substring(10,11);         // vidare det till keypad classen för behandling
        if(keyPad.keyPadInput(button)){                                        // och uppdaterar keypaden på GUI
            keyPadDisplay.setText(keyPad.getDisplayMessage());
            lights();
            if(!cu.isActive()){
               resetGui();

               timer.purge();
            }

        }else keyPadDisplay.setText(keyPad.getDisplayInput());
    }

    public void resetGui(){
        for(Rectangle theObject: colliSionObjects){
            if(theObject.getId().substring(2,3).equalsIgnoreCase("W")){       // Återställer alla dörrar och fönster på GUI
                theObject.setFill(Color.LIGHTBLUE);
            }else theObject.setFill(Color.GHOSTWHITE);
        }
    }





    public void uppdateLog(){
       logTerminal.appendText(cu.getLoggEntry());
    }

    public void clearLog(ActionEvent event){
        logTerminal.setText("");
    }

    public void collisionTest() throws IOException {
        for(Rectangle theObject: colliSionObjects ){
            if(theObject.getBoundsInParent().intersects(Thief.getBoundsInParent()) && cu.isActive()){   // kontrollerar ifall tjuv objektet
                theObject.setFill(Color.RED);                                                           // kolliderar med någon dörr, fönster
                alarmCheck(theObject);                                                                  // eller rörelsedetektor
            }
        }
    }
    public void alarmCheck(Rectangle theObject) throws IOException {                  // Kontrollerar om larmet skall gå igång.
        boolean start = true;                                                         // hämtar id från dom grafiska representationen av alla objekt
        id = theObject.getId().substring(0,1);                                        // och kontrollerar och utför åtgärder på motsvarande objekt.
        int number = Integer.parseInt(theObject.getId().substring(1,2));
        type = theObject.getId().substring(2,3);
        index = Integer.parseInt(theObject.getId().substring(3,4));
        id = house.convertRoom(id, number);
        type = house.convertType(type);
        if(type.equalsIgnoreCase("Motion") && !cu.isTriggerd()){
            motionAlarm();
            start = false;

        }else if( cu.isActive() && !cu.isTriggerd() ) {
            start = delayedAlarm();
        }
        if  (start && !cu.isTriggerd()) {
            house.open(house.findRoom(id), type, index);
            timer.scheduleAtFixedRate(alarmTask(burglarAlarm),0,500);
            lights();
            uppdateLog();
        }






    }

    public boolean delayedAlarm(){
        if (id.equalsIgnoreCase("Hallway") && type.equalsIgnoreCase("Door")) {       // kontrollerar ifall det ska var fördröjt alarm
            if(!timerTriggerd) {
                timer.scheduleAtFixedRate(countDownTask(), 0, 1000);
            }
            return false;


        } else if (id.equalsIgnoreCase("Living Room") && type.equalsIgnoreCase("Door")) {
            if(!timerTriggerd) {
                timer.scheduleAtFixedRate(countDownTask(), 0, 1000);
            }
            return false;

        } else if (id.equalsIgnoreCase("Garage") && type.equalsIgnoreCase("Door")) {
            if(!timerTriggerd) {
                timer.scheduleAtFixedRate(countDownTask(), 0, 1000);
            }
            return  false;
        }


            return true;

    }

    public void motionAlarm() throws IOException {
        house.findRoom(id).getMotionDetector().trigger();
        lights();
        uppdateLog();
        if(!sirenTriggerdGlobal && cu.isActive()  ) {
            sirenTriggerdGlobal = true;
            timer.scheduleAtFixedRate(alarmTask(burglarAlarm), 0, 500);

        }

    }

    public void fire(ActionEvent e) throws IOException {                            // hämtar vilket rum användaren vill start rökdetekorn i
        id = e.getSource().toString().substring(12,13);                             // samt kontrollerar ifall brandlarmet skall startas
        index = Integer.parseInt(e.getSource().toString().substring(13,14));
        id = house.convertRoom(id,index);

        if(!cu.isFire() ){
            timer.scheduleAtFixedRate(fireTask(fireAlarm,id),0,1000);
        }else timer.scheduleAtFixedRate(sprinklerActivation(id),0,1000);
        house.findRoom(id).getSmokeDetector().trigger();
        lights();
        uppdateLog();

    }




    public TimerTask countDownTask(){
        TimerTask task = new TimerTask(  ) {
            @Override
            public void run() {
                timerTriggerd = true;
                countDownTf.setVisible(true);
                //fade2.play();
                if (counter > 0 && cu.isActive()) {
                    countDownTf.setText("Alarm goes of in " + counter + " Seconds" );
                    keyPad.beep();


                    counter--;
                } else if (counter <= 0 && cu.isActive()) {
                    house.open(house.findRoom(id), type, index);
                    uppdateLog();
                    timer.scheduleAtFixedRate(alarmTask(burglarAlarm),0,500);
                    countDownTf.setVisible(false);
                    counter = 15;
                    timerTriggerd = false;
                    this.cancel();
                    try {
                        lights();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(!cu.isActive() || cu.isTriggerd()) {
                    countDownTf.setVisible(false);
                    counter = 15;
                    timerTriggerd = false;
                    this.cancel();
                }
            }
        };


        return task;
    }

    public TimerTask alarmTask(File file){
        TimerTask task = new TimerTask() {
            Clip clip;
            Boolean sirenTriggerd = false;
            @Override
            public void run() {

                if(cu.isTriggerd() && !sirenTriggerd){
                    sirenTriggerd = true;
                    try{
                        clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(file));
                        clip.start();
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }else if(!clip.isRunning()){
                    sirenTriggerd = false;

                }
                else if(!cu.isActive()) {
                    clip.close();
                    sirenTriggerdGlobal = false;
                    sirenTriggerd = false;
                    this.cancel();
                }
            }
        };
        return task;
    } public TimerTask fireTask(File file, String id){
        TimerTask task = new TimerTask() {
            int counter;
            boolean running;
            Clip clip;

            @Override
            public void run() {

                counter ++;
                if(cu.isFire() && counter > 10 && !house.findRoom(id).getSprinkler().isTriggerd()){
                      house.findRoom(id).getSprinkler().trigger();
                      uppdateLog();


                }
                if(!running){
                    running = true;
                    try{
                        clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(file));
                        clip.start();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else if(!clip.isRunning()){
                    running = false;
                }else if(!cu.isFire()){
                    clip.close();
                    running = false;
                    this.cancel();
                }
            }
        };
        return task;

    }

    public TimerTask sprinklerActivation(String id){
        TimerTask task = new TimerTask() {
            int counter = 0;
            @Override
            public void run() {
                counter ++;
                if(counter > 10 && !house.findRoom(id).getSprinkler().isTriggerd()){
                    house.findRoom(id).getSprinkler().trigger();
                    uppdateLog();
                    this.cancel();
                }
            }
        };
        return  task;

    }


    public void resetFireAlarm(ActionEvent event){
        cu.resetFireAlarm();
    }


    public void exit(ActionEvent event){
        System.exit(0);
    }



}