package com.example.Alarm;

public class DoorDetector extends Detector{


    public DoorDetector(String location){
        setLocation(location);
        deactivate();

    }


    @Override
    String getType() {
        return "DoorDetecor";
    }

    @Override
    void reset() {
        deactivate();
        resetTrigger();
        resetTriggerCount();
    }

}
