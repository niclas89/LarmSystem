package com.example.Alarm;

import House.Door;
import House.Room;
import House.Window;
import java.util.*;


public class CentralUnit  {
    private final String location = "Garage";
    private String loggEntry;
    private boolean active;
    private boolean alarmTriggerd;
    private boolean fire;

    private Timer timer = new Timer();


    private final int passCode = 2478;

    private ArrayList<WindowDetector> windowDetectors = new ArrayList<>();
    private ArrayList<DoorDetector> doorDetectors = new ArrayList<>();

    private ArrayList<SmokeDetector> smokeDetectors = new ArrayList<>();
    private ArrayList<MotionDetector> motionDetectors = new ArrayList<>();

    private ArrayList<Sprinkler> sprinklers = new ArrayList<>();






    public void soundAlarm(Detector detector)  {
        if(detector.getType().equalsIgnoreCase("SmokeDetector") && detector.getTriggerCount() <2){ // Kontrollerar vilken detektor som larmat
            loggEntry = "Systemlog Entry:  Attention Fire in "+ detector.getLocation() +"\n";                 // och skapar loggEntry med information
            fire = true;



        } else if(detector.isActive() &&  detector.isTriggerd() && detector.getTriggerCount() < 2){
            loggEntry = "Systemlog Entry:  Alarm triggerd at " + detector.getLocation() + "\n By " +
                    detector.getType() +
                    "At " + getTime() +"\n"  ;
            alarmTriggerd = true;

        }else loggEntry = "";
    }

    public void sprinklerActivated(Sprinkler sprinkler){
        if(sprinkler.isTriggerd() && sprinkler.getTriggerCount() <2){
            loggEntry = "Systemlog Entry:  Sprinkler activated at " + sprinkler.getLocation() +  "\n";
        }



    }
    public String getTime(){
        Date date = new Date();
        return date.toString();

    }
    private boolean checkPassCode(int a){
        if(a == passCode){
          return true;
        }else return false;

    }

    public String getLoggEntry(){
        return loggEntry;
    }

    public void connectSystem(ArrayList<Room> roomImport) {                        // tar emot huset och anslutar sig själv med alla detektorer
                                                                                   // så dom kan kommunicerar direkt utan att gå via House classen
        for (Room theRom : roomImport) {
            if(!theRom.getLocation().equalsIgnoreCase("Backyard")) {
                smokeDetectors.add(theRom.getSmokeDetector());
                theRom.getSmokeDetector().setCu(this);
                theRom.getSprinkler().setCu(this);
            }
            if(theRom.getLocation().equalsIgnoreCase("Backyard") || theRom.getLocation().equalsIgnoreCase("Hallway")
            || theRom.getLocation().equalsIgnoreCase("Living room")){
                motionDetectors.add(theRom.getMotionDetector());
                theRom.getMotionDetector().setCu(this);
            }

            for (Window theWindow : theRom.getWindowList()) {
                windowDetectors.add(theWindow.getDetector());
                theWindow.getDetector().setCu(this);
            }
            for (Door theDoor : theRom.getDoorList()) {
                doorDetectors.add(theDoor.getDetector());
                theDoor.getDetector().setCu(this);

            }
        }



    }
    public boolean activateAllSystems(int passCode){                    // Aktiverar alla detektorer
        if(checkPassCode(passCode)) {
            active = true;
            fire = false;
            alarmTriggerd = false;
            for (WindowDetector detector : windowDetectors) {
                detector.activate();
            }
            for (DoorDetector detector : doorDetectors) {
                detector.activate();
            }
            for (MotionDetector detector : motionDetectors) {
                detector.activate();
            }
            for(SmokeDetector detector: smokeDetectors){
                detector.activate();
            }
            return  true;
        }
        return false;
    }

    public boolean deactiveAllSystem(int passCode){           // återställar alla detektorer
        if(checkPassCode(passCode)) {
            active = false;
            alarmTriggerd = false;
            fire = false;
            for (WindowDetector detector : windowDetectors) {
                detector.reset();
            }
            for (DoorDetector detector : doorDetectors) {
                detector.reset();
            }
            for (MotionDetector detector : motionDetectors) {
                detector.reset();
            }
            for(SmokeDetector detector : smokeDetectors){
                detector.reset();
            }
            return true;
        }
        return false;
    }

    public void resetFireAlarm(){
        fire = false;
        for(SmokeDetector detector: smokeDetectors){
            detector.reset();

        }
        for(Sprinkler sprinkler: sprinklers){
            sprinkler.reset();
        }
    }

    public boolean isActive(){
        return  active;
    }
    public boolean isTriggerd(){
        return alarmTriggerd;
    }
    public boolean isFire(){
        return fire;
    }




}
