package com.example.Alarm;

public class Sprinkler {



    private boolean active;
    private boolean triggerd;

    private int triggerCount;

    private String location;
    private CentralUnit cu;



    public Sprinkler(String location){
        this.location = location;
        activate();
    }


    public void setCu(CentralUnit cu){
        this.cu  = cu;

    }
    public void activate(){
        this.active = true;
    }


    public boolean isActive(){
        return active;
    }
    public boolean isTriggerd(){
        return triggerd;
    }
    public void trigger(){
        triggerd = true;
        triggerCount ++;
        cu.sprinklerActivated(this);

    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return location;
    }
    public void reset(){
        triggerd = false;
        triggerCount = 0;
    }
    public int getTriggerCount(){
        return triggerCount;
    }
}
