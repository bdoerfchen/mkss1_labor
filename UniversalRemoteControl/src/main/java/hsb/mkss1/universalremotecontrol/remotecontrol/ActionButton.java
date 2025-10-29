package hsb.mkss1.universalremotecontrol.remotecontrol;

public class ActionButton {

    private final ICommand command;

    public ActionButton(ICommand command) {
        this.command = command;
    }

    public void invoke(){
        command.execute();
    }


}
