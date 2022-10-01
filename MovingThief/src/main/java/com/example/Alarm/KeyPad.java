package com.example.Alarm;

import javax.swing.*;
import java.security.Key;

public class KeyPad {

    private CentralUnit cu;
    private String displayMessage = "";
    private final String [] messages = {"Alarm Active","Alarm not Active", "Alarm Triggerd see Control Log", "Incorrect Password"};
    private String displayInput = "";
    private boolean Password ;





    public KeyPad(){
        Password = true;
    }

    public boolean keyPadInput(String input){
        if(input.equalsIgnoreCase("P")) {
          if(!cu.isActive()){
              Password = cu.activateAllSystems(Integer.parseInt(displayInput)); // kontrollerar vilken knapp användaren tryckta på
              displayInput = "";                                                // och uppdaterar display eller kontaktar central unit ifall
                                                                                // användaren markerat att den tryckt in lösen
                  setDisplayMessage();
                  return true;

          }else if(cu.isActive()) {
              Password = cu.deactiveAllSystem(Integer.parseInt(displayInput));
              displayInput = "";

              setDisplayMessage();
              return true;

          }


        } else displayInput += input;
        return false;



    }


    public void setDisplayMessage(){

        if(cu.isActive()&& cu.isTriggerd()){                       // Sätter displayMessage
            displayMessage = messages[2];
        }else if(cu.isActive() && !cu.isTriggerd()){
            displayMessage = messages[0];
        }else if(!cu.isActive()){
            displayMessage = messages[1];
        }
        if(!Password){
            displayMessage = messages[3];
        }

    }
    public String getDisplayMessage(){
         return displayMessage;
    }
    public String getDisplayInput(){
        return displayInput;
    }
    public void setCu(CentralUnit cu){
        this.cu = cu;
    }
    public void beep(){
        java.awt.Toolkit.getDefaultToolkit().beep();
    }

}
