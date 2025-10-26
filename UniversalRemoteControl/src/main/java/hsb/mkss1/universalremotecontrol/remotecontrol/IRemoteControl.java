package hsb.mkss1.universalremotecontrol.remotecontrol;

/**
 * Interface for a generic remote control.
 */
public interface IRemoteControl {


    void configureButton(int buttonNumber, ICommand command);

    /**
     * The action button was pressed.
     * Depending on its status, it will execute an activate or deactivate action.
     *
     * @param no The number of the button.
     */
    void actionButtonPressed(int no);

    /**
     * The undo button was pressed.
     * It will undo the previous action.
     */
    void undoButtonPressed();
}
