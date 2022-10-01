package com.example.Alarm;

import House.Window;

public class WindowDetector extends Detector{

     private  int id;
    public WindowDetector(String location ){
        setLocation(location);

    }

    @Override
    String getType() {
        return "WindowDetector";
    }

    @Override
    void reset() {
        deactivate();
        resetTrigger();
        resetTriggerCount();

    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
