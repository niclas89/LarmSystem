package com.example.Alarm;

public class MotionDetector extends Detector{

    public MotionDetector(String location){
        this.setLocation(location);


    }

    @Override
    String getType() {
        return "MotionDetector";
    }

    @Override
    void reset() {
        resetTrigger();
        deactivate();
        resetTriggerCount();
    }
}
