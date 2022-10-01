package House;

import com.example.Alarm.WindowDetector;

public class Window implements DoorWindowInterface{
    private String location;
    private WindowDetector detector;
    private boolean open;
    private boolean broken;

    public  Window(String location){
        setLocation(location);
        close();
        this.detector = new WindowDetector(location);

    }


    @Override
    public void setLocation(String location) {
        this.location = location;

    }

    @Override
    public String getLocation() {
        return location;

    }


    public void setDetector(WindowDetector detector) {
        this.detector = detector;

    }

    @Override
    public WindowDetector getDetector() {
        return detector;

    }

    @Override
    public void open() {
        this.open = true;
        detector.trigger();

    }

    @Override
    public void close() {
        this.open = false;

    }

    @Override
    public boolean isOpen() {
        return open;

    }

    public void brake(){
        this.broken = true;
    }
    public void fix(){
        this.broken = false;
    }
}

