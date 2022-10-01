package House;

import com.example.Alarm.MotionDetector;
import com.example.Alarm.SmokeDetector;
import com.example.Alarm.Sprinkler;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String location;

    private SmokeDetector smokeDetector;
    private MotionDetector detector;
    private Sprinkler sprinkler;
    private ArrayList<Window> windowList = new ArrayList<Window>();
    private ArrayList<Door>   doorList = new ArrayList<Door>();

    Room(String location, int windows, int doors){
        setLocation(location);

        for(int i = 0; i<windows; i++){
            windowList.add(new Window(getLocation()));
        }
        for(int i = 0; i<doors; i++){
            doorList.add(new Door(getLocation()));
        }


    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return location;
    }

    public List<Window> getWindowList(){
        return windowList;
    }
    public List<Door> getDoorList(){
        return doorList;
    }

    public MotionDetector getMotionDetector(){
        return  detector;

    }
    public void setMotionDetector(MotionDetector detecor){
        this.detector = detecor;
    }

    public void setSmokeDetector(SmokeDetector smokeDetector) {
        this.smokeDetector = smokeDetector;
    }

    public SmokeDetector getSmokeDetector(){
        return this.smokeDetector;
    }

    public void setSprinkler(Sprinkler sprinkler){
        this.sprinkler = sprinkler;
    }

    public Sprinkler getSprinkler(){
        return this.sprinkler;
    }
}

