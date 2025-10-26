package hsb.mkss1.universalremotecontrol.commands;

public class PauseUnpauseCommand extends ToggleCommand {


    public void activateFunctionality(){
        IO.println("Pause");
    }

    public void deactivateFunctionality(){
        IO.println("Unpause");
    }


}
