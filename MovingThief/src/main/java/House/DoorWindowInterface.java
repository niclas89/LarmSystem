package House;

import com.example.Alarm.Detector;


public interface DoorWindowInterface {

     void setLocation(String location);
     String getLocation();

     Detector getDetector();
     void open();
     void close();
     boolean isOpen();

}
