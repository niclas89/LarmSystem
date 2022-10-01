package com.example.Alarm;


public abstract class Detector {

    private String location;
    private boolean active;
    private boolean triggerd;

    private int triggerCount;

    private CentralUnit cu;

    void uppdateTriggerCount(){
        triggerCount ++;
    }
     int getTriggerCount(){
        return triggerCount;
    }

    void activate(){
        this.active = true;
    };
    void deactivate(){
        this.active = false;
    };
    boolean isActive(){
        return active;
    };
    public void trigger() {
        this.triggerd = true;
        uppdateTriggerCount();
        cu.soundAlarm(this);

    };
    abstract String getType();


    void resetTrigger(){
        this.triggerd = false;
    }
    boolean isTriggerd(){
        return triggerd;
    };
    String getLocation(){
        return location;
    };

    void setLocation(String location){
        this.location = location;
    };

    abstract void reset();

    void setCu(CentralUnit input){
        this.cu = input;

    }
    void resetTriggerCount(){
        triggerCount = 0;
    }


}
