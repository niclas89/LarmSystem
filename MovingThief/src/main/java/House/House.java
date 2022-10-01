package House;

import com.example.Alarm.MotionDetector;
import com.example.Alarm.SmokeDetector;
import com.example.Alarm.Sprinkler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class House {



    private List<Room> roomList = new ArrayList<>();
    public static List<String> roomBluePrint(){
        return Arrays.asList("Garage", "Living room", "Kitchen", "Bedroom1", "Bedroom2", "Bedroom3", "Bedroom4", "Bedroom5",
        "Hallway", "Backyard");
    }
    public static List<Integer> windowBluePrint(){
        return Arrays.asList(1,2,1,2,1,1,2,1,1,0);
    }
    public static List<Integer> doorBluePrint(){
        return Arrays.asList(2,1,0,1,1,1,1,1,1,0);
    }
    public House(){
        int i = 0;
        for(String name: roomBluePrint()){
            roomList.add(new Room(name, windowBluePrint().get(i), doorBluePrint().get(i) ));            //Skapar huset enligt inlagda ritningar
            if(!name.equalsIgnoreCase("Backyard")) {
                findRoom(name).setSmokeDetector(new SmokeDetector(name));
                findRoom(name).setSprinkler(new Sprinkler(name));
            }
            if(name.equalsIgnoreCase("Backyard") || name.equalsIgnoreCase("Hallway") ||
                    name.equalsIgnoreCase("Living room")){

                findRoom(name).setMotionDetector(new MotionDetector(name));
            }
            i++;
        }

    }
    public ArrayList exportHouse(){
        return (ArrayList) roomList;
    }

    public Room getRoom(int i){
        return roomList.get(i);
    }

    public Room findRoom(String name){
        for(Room theRoom: roomList){
            if(theRoom.getLocation().equalsIgnoreCase(name)){
                return theRoom;
            }
        }
       return null;
    }
    public void open(Room a,String b,int c){
        if(b.equalsIgnoreCase("door"))
        a.getDoorList().get(c).open();

        if(b.equalsIgnoreCase("Window")){
            a.getWindowList().get(c).open();
        }
    }


    public String convertRoom(String id,int i){         //konventera fxid
        String name = "";

        switch (id) {
            case "B": name = "Bedroom" +i;
            break;
            case "G": name = "Garage"  ;
            break;
            case "K": name = "Kitchen";
            break;
            case "L": name = "Living room";
            break;
            case "H": name = "Hallway";
            break;
            case "Y": name = "BackYard";
            break;
        }


        return name ;
    }

    public String convertType(String id){        // konventerar fxid
        String type = " ";
      switch (id){
          case "W": type = "window";
          break;
          case "D": type = "Door";
          break;
          case "M": type = "Motion";
          break;
      }
      return type;
    }





}
