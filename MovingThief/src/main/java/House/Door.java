package House;


import com.example.Alarm.DoorDetector;

public class Door implements DoorWindowInterface{
    private String location;
    private boolean open;
    private DoorDetector detector;

    public void setDetector(DoorDetector detector){
        this.detector = detector;
    }

    public  Door(String location){
        this.location = location;
        this.detector = new DoorDetector(location);
        close();
    }
    @Override
    public void setLocation(String location) {

    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public DoorDetector getDetector() {
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
}
