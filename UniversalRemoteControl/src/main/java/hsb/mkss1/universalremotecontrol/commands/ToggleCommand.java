package hsb.mkss1.universalremotecontrol.commands;

import hsb.mkss1.universalremotecontrol.remotecontrol.ICommand;

public abstract class ToggleCommand implements ICommand {

    private boolean state = false;

    public abstract void activateFunctionality();

    public abstract void deactivateFunctionality();

    @Override
    public final void execute() {
        if(state){
            deactivateFunctionality();
        } else {
            activateFunctionality();
        }
        state = !state;
    }
}
