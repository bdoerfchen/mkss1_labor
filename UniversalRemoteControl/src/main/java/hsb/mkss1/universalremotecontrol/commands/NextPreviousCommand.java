package hsb.mkss1.universalremotecontrol.commands;

public class NextPreviousCommand extends ToggleCommand {


    public void activateFunctionality(){
        IO.println("Next");
    }

    public void deactivateFunctionality(){
        IO.println("Previous");
    }


}
