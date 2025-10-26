package hsb.mkss1.universalremotecontrol.commands;

public class OnOffCommand extends ToggleCommand {

    public void activateFunctionality() {
        IO.println("On");
    }

    public void deactivateFunctionality() {
        IO.println("Off");
    }

}
