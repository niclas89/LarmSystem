package com.example.Alarm;

public class SmokeDetector extends Detector{

    private final boolean active  = true;

    public SmokeDetector(String location){
        this.setLocation(location);

    }

    @Override
    void reset() {
        resetTrigger();
        resetTriggerCount();
    }
    @Override
    void deactivate(){

    }

    @Override
    String getType() {
        return "SmokeDetector";
    }
}
